package com.example.p1_ppm

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navArgs


@Composable
fun setupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.ruta
    )   {

        composable(
            route = Screens.Home.ruta
        ){
            crearUsuario(navController = navController)
        }
        /*
        composable(
            route = Screens.LogIn.ruta,
            arguments = listOf(
                navArgument(USUARIO_ARGUMENT_KEY1){
                    type = NavType.StringType
                },
                navArgument(USUARIO_ARGUMENT_KEY2){
                    type = NavType.StringType
                }
            )
        ){
            Log.d("Args", it.arguments?.getString(USUARIO_ARGUMENT_KEY1).toString())
            Log.d("Args", it.arguments?.getString(USUARIO_ARGUMENT_KEY2).toString())
            Isesion(navController=navController,
                it.arguments?.getString(USUARIO_ARGUMENT_KEY1).toString(),
                it.arguments?.getString(USUARIO_ARGUMENT_KEY2).toString())

        }*/

    }
}
