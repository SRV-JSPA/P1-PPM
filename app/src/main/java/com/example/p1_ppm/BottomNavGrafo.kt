package com.example.p1_ppm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavBar_fun(navController:NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.ruta
    ){
        composable(route = BottomBarScreen.Home.ruta){
            calendarioTutor_fun(navController = navController)
        }
        composable(route = BottomBarScreen.Calendario.ruta){
            calendarioAlumno_fun(navController = navController)
        }
        composable(route = BottomBarScreen.Perfil.ruta){
            UserProfileScreen(navController=navController)
        }
    }
}