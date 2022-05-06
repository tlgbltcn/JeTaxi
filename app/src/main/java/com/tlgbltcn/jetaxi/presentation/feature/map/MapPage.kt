package com.tlgbltcn.jetaxi.presentation.feature.map

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.presentation.ui.components.*
import com.tlgbltcn.jetaxi.util.getMarkerIcon
import kotlinx.coroutines.launch
import radiography.ExperimentalRadiographyComposeApi
import radiography.Radiography
import radiography.ViewFilters.skipComposeTestTagsFilter
import radiography.ViewStateRenderers.DefaultsIncludingPii

internal const val LIVE_HIERARCHY_TEST_TAG = "live-hierarchy"

@OptIn(ExperimentalRadiographyComposeApi::class)
@ExperimentalMaterialApi
@Composable
fun MapPage(
    viewModel: MapViewModel
) {
    val liveHierarchy = remember { mutableStateOf<String?>(null) }
    val state by viewModel.uiState.collectAsState()

    Column {

        val prettyHierarchy = Radiography.scan()
        Log.d("debug-drawer", prettyHierarchy)

        if (state.isLoading) {
            ProgressView()
        } else {
            when (state) {
                is TaxisState.Error -> ErrorView()

                is TaxisState.Content -> {
                    (state as TaxisState.Content).taxis?.let { taxis ->
                        MapView(taxis) {
                            MapViewContainer(this@MapView, taxis)
                        }
                    }
                }
            }
        }

        SideEffect {
            liveHierarchy.value = Radiography.scan(
                viewStateRenderers = DefaultsIncludingPii,
                viewFilter = skipComposeTestTagsFilter(LIVE_HIERARCHY_TEST_TAG)
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MapViewContainer(
    map: MapView,
    taxis: List<Taxis.Poi>
) {

    val context = LocalContext.current
    var zoom by rememberSaveable(map) { mutableStateOf(8f) }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val taxi = taxis.shuffled().first()
    var selectedTaxi by remember {
        mutableStateOf(taxi)
    }
    val cameraPosition =
        remember(selectedTaxi.coordinate.latitude, selectedTaxi.coordinate.latitude) {
            LatLng(
                selectedTaxi.coordinate.latitude,
                selectedTaxi.coordinate.longitude
            )
        }

    LaunchedEffect(map) {
        val googleMap = map.awaitMap()
        taxis.forEach { taxi ->
            googleMap.addMarker {
                position(
                    LatLng(taxi.coordinate.latitude, taxi.coordinate.longitude)
                )
                icon(
                    BitmapDescriptorFactory.fromBitmap(
                        getMarkerIcon(
                            context = context,
                            fleetType = taxi.fleetType,
                        )
                    )
                )
                rotation(taxi.heading.toFloat())
            }
        }

        googleMap.apply {
            setOnMarkerClickListener { p0 ->
                taxis.find {
                    it.coordinate.latitude == p0.position.latitude &&
                        it.coordinate.longitude == p0.position.longitude
                }?.let {
                    selectedTaxi = it
                }
                true
            }

            animateCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetContent = {
            LazyColumn {
                items(taxis) { item ->
                    TaxiItemRow(data = item, onClick = { selectedCoordinate ->
                        selectedTaxi = item
                        zoom = 16f
                        if (bottomSheetState.isVisible) {
                            coroutineScope.launch {
                                bottomSheetState.hide()
                            }
                        }
                    })
                }
            }
        }
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            AndroidView({ map }) { mapView ->
                coroutineScope.launch {
                    mapView.awaitMap().apply {
                        animateCamera(CameraUpdateFactory.newLatLngZoom(cameraPosition, zoom))
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter),
                horizontalArrangement = Arrangement.Center
            ) {

                SelectedTaxiItemRow(modifier = Modifier.weight(7f), data = selectedTaxi)

                MapZoomButtons(modifier = Modifier.weight(1f), zoom) {
                    zoom = it.coerceIn(2f, 20f)
                }
            }

            BottomSheetButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState
            )
        }
    }
}
