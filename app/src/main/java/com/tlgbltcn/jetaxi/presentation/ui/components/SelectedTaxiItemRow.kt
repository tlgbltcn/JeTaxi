package com.tlgbltcn.jetaxi.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tlgbltcn.jetaxi.domain.model.Taxis

@Composable
fun SelectedTaxiItemRow(modifier: Modifier = Modifier, data: Taxis.Poi) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp), elevation = 8.dp
    ) {

        Column(
            modifier = Modifier
                .padding(all = 8.dp)
        ) {
            Text(data.fleetType.name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "ID: ${data.id}", style = MaterialTheme.typography.body2)
                Text(
                    "Latitude: ${data.coordinate.latitude}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    "Longitude: ${data.coordinate.longitude}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}