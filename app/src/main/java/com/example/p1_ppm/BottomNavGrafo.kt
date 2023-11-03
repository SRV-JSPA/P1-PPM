package com.example.p1_ppm

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p1_ppm.Managers.FirestoreManager
import com.example.p1_ppm.screens.login.student.Hestudiante_fun
import com.example.p1_ppm.screens.login.student.SearchScreen
import com.example.p1_ppm.screens.login.student.UsuarioA_fun
import com.example.p1_ppm.screens.login.student.calendarioAlumno_fun
import com.example.p1_ppm.screens.login.teacher.Htutor_fun
import com.example.p1_ppm.screens.login.teacher.UsuarioT_fun
import com.example.p1_ppm.screens.login.teacher.calendarioTutor_fun

@Composable
fun BottomNavBar_fun(navController:NavHostController, firestore: FirestoreManager, context: Context) {
    val firestore = FirestoreManager(context)
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
            Htutor_fun(navController = navController, firestore = firestore)
        }
        composable(route = BottomBarScreen.CalendarioT.ruta){
            calendarioTutor_fun(navController = navController)
        }
        composable(route = BottomBarScreen.PerfilT.ruta){
            UsuarioT_fun(navController=navController)
        }
    }
}