package com.shegs.idme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "input"
    ){
        composable("input") {
            InputInfo{ qrText ->
                // Navigate to the display QR code screen and pass the QR text
                navController.navigate("display/$qrText")
            }
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
    }
}