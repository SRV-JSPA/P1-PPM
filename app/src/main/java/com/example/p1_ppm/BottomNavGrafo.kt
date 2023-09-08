package com.example.p1_ppm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavBar_fun(navController:NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.HomeA.ruta
    ){
        composable(route = BottomBarScreen.HomeA.ruta){
            Hestudiante_fun(navController = navController)
        }
        composable(route = BottomBarScreen.CalendarioA.ruta){
            calendarioAlumno_fun(navController = navController)
        }
        composable(route = BottomBarScreen.BuscarA.ruta){
            SearchScreen(navController=navController)
        }
        composable(route = BottomBarScreen.PerfilA.ruta){
            UsuarioA_fun(navController=navController)
        }
        composable(route = BottomBarScreen.HomeT.ruta){
            Htutor_fun(navController = navController)
        }
        composable(route = BottomBarScreen.CalendarioT.ruta){
            calendarioTutor_fun(navController = navController)
        }
        composable(route = BottomBarScreen.PerfilT.ruta){
            UsuarioT_fun(navController=navController)
        }
    }
}