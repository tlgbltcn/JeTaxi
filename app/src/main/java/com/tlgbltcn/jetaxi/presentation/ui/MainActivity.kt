package com.tlgbltcn.jetaxi.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.tlgbltcn.jetaxi.presentation.ui.navigation.JeTaxiNavGraph
import com.tlgbltcn.jetaxi.presentation.ui.theme.JeTaxiTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JeTaxiTheme {
                JeTaxiNavGraph()
            }
        }
    }
}