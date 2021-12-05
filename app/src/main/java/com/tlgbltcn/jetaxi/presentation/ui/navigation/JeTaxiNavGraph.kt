package com.tlgbltcn.jetaxi.presentation.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.tlgbltcn.jetaxi.presentation.feature.map.MapPage
import com.tlgbltcn.jetaxi.presentation.feature.map.MapViewModel

const val HOME = "/"

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun JeTaxiNavGraph(
    startDestination: String = HOME
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME) { navBackStackEntry ->
            val viewModel: MapViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            )
            MapPage(
                viewModel = viewModel,
            )
        }
    }
}


