package com.shegs.idme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shegs.idme.ui.DashboardScreen
import com.shegs.idme.ui.DisplayQRScreen
import com.shegs.idme.ui.InfoDetailsScreen
import com.shegs.idme.ui.InputInfoScreen
import com.shegs.idme.utils.generateQRCode
import com.shegs.idme.viewModels.CardViewModel
import com.shegs.idme.viewModels.InfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: CardViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(
            "input/{cardName}",
            arguments = listOf(navArgument("cardName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cardName = backStackEntry.arguments?.getString("cardName") ?: ""
            val infoViewModel: InfoViewModel = hiltViewModel()
            InputInfoScreen(cardName = cardName, infoViewModel = infoViewModel, onGenerateQRCode = { userInfo ->
                val qrText = generateQRCode(userInfo)
                navController.navigate("display/$qrText")
            })
        }

        composable(
            "display/{qrText}",
            arguments = listOf(navArgument("qrText") { type = NavType.StringType })
        ) { backStackEntry ->
            val qrText = backStackEntry.arguments?.getString("qrText") ?: ""
            val bitmap = generateQRCode(qrText, 300)
            val imageBitmap: ImageBitmap = (bitmap.asImageBitmap())
            DisplayQRScreen(imageBitmap)
        }

        composable(
            "info/{infoId}",
            arguments = listOf(navArgument("infoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val infoId = backStackEntry.arguments?.getInt("infoId") ?: 0
            val infoViewModel: InfoViewModel = hiltViewModel()

            val infoState by infoViewModel.infoState.collectAsState()

            // Fetch info details if not already fetched
            if (infoState == null) {
                infoViewModel.fetchInfoById(infoId)
            }

            infoState?.let {
                InfoDetailsScreen(infoId = infoId, viewModel = infoViewModel)
            }
        }
    }
}
