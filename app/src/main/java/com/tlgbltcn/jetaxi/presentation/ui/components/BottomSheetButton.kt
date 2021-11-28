package com.tlgbltcn.jetaxi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tlgbltcn.jetaxi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetButton(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
) {
    Row(
        modifier = modifier
            .padding(all = 16.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = MaterialTheme.colors.primary)
            .clickable {
                coroutineScope.launch {
                    if (bottomSheetState.isVisible) {
                        bottomSheetState.hide()
                    } else {
                        bottomSheetState.show()
                    }
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            tint = MaterialTheme.colors.background,
            painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
            contentDescription = null
        )
    }
}