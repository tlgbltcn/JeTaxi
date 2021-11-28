package com.tlgbltcn.jetaxi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.tlgbltcn.jetaxi.R

@Composable
fun ErrorView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(width = 200.dp, height = 200.dp),
            painter = painterResource(id = R.drawable.ic_panda_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.error),
            style = TextStyle(fontFamily = FontFamily.Monospace)
        )
    }
}