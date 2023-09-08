package com.example.p1_ppm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


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
            route = Screens.LogIn.ruta,
            arguments = listOf(
                navArgument(USUARIO_ARGUMENT_KEY1){
                    type = NavType.StringType
                }
        )){
            sesion(it.arguments?.getString(USUARIO_ARGUMENT_KEY1).toString(),
                navController = navController)
        }
        composable(
            route = Screens.SignIn.ruta,
            arguments = listOf(
                navArgument(SIGNIN_ARGUMENT_KEY1){
                    type = NavType.StringType
                }
            )){
            crearUsuario(it.arguments?.getString(SIGNIN_ARGUMENT_KEY1).toString(),
                navController = navController)
        }

        composable(
            route = Screens.PaginaP.ruta,
            arguments = listOf(
                navArgument(PAGINAP_ARGUMENT_KEY1){
                    type = NavType.BoolType
                }
            )
        ){
            val tem = it.arguments?.getBoolean(PAGINAP_ARGUMENT_KEY1)
            if(tem==null){
                paginaPrincipal_fun(false)
            }
            else{
                paginaPrincipal_fun(tem)
            }

        }
    }
}
