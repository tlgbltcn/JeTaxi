package com.tlgbltcn.jetaxi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tlgbltcn.jetaxi.R
import com.tlgbltcn.jetaxi.domain.model.FleetType
import com.tlgbltcn.jetaxi.domain.model.Taxis

@Composable
fun TaxiItemRow(
    modifier: Modifier = Modifier,
    data: Taxis.Poi,
    onClick: ((Taxis.Poi.Coordinate?) -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.background)
            .clickable(onClick = {
                onClick?.invoke(data.coordinate)
            })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .size(60.dp)
                .clip(CircleShape)
                .requiredSize(60.dp)
                .background(
                    when (data.fleetType) {
                        FleetType.POOLING -> colorResource(id = R.color.orange)
                        FleetType.TAXI -> colorResource(id = R.color.yellow)
                    }
                )
        )
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(data.fleetType.name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "ID: ${data.id}", style = MaterialTheme.typography.body1)
                Text(
                    "Latitude: ${data.coordinate.latitude}",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    "Longitude: ${data.coordinate.longitude}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}