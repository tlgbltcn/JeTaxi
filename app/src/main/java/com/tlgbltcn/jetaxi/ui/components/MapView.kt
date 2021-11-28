package com.tlgbltcn.jetaxi.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.libraries.maps.MapView
import com.tlgbltcn.jetaxi.R
import com.tlgbltcn.jetaxi.domain.model.Taxis
import com.tlgbltcn.jetaxi.util.rememberMapViewWithLifecycle

@ExperimentalMaterialApi
@Composable
fun MapView(
    taxis: List<Taxis.Poi>,
    content: @Composable MapView.() -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    content(mapView)
}

@Composable
fun MapZoomButtons(
    modifier: Modifier = Modifier,
    zoom: Float,
    onZoomChanged: (Float) -> Unit
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        ZoomButton(R.drawable.ic_baseline_add_24, onClick = { onZoomChanged(zoom * 1.2f) })
        Spacer(modifier = Modifier.padding(4.dp))
        ZoomButton(R.drawable.ic_baseline_remove_24, onClick = { onZoomChanged(zoom * 0.8f) })
    }
}

@Composable
private fun ZoomButton(@DrawableRes id: Int, onClick: () -> Unit) {

    IconButton(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(8.dp)
            )
            .size(36.dp)
            .background(color = MaterialTheme.colors.background),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
    }
}