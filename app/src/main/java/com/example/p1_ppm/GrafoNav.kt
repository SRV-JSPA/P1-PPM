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
            Inicio_Screen(navController = navController)
        }
        composable(
            route = Screens.LogIn.ruta
        ){
            sesion("Juan","123",navController = navController)
        }
        composable(
            route = Screens.SignIn.ruta
        ){
            crearUsuario(navController = navController)
        }
    }
}
