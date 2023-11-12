package com.example.p1_ppm.navigation



import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.Managers.AnalyticsManager
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.Managers.RealtimeManager
//import com.example.p1_ppm.Managers.FirestoreManager
import com.example.p1_ppm.screens.Screen
import com.example.p1_ppm.screens.login.paginaPrincipal_fun
import com.example.p1_ppm.screens.auth.ForgotPassword
import com.example.p1_ppm.screens.auth.Login
import com.example.p1_ppm.screens.auth.SignUp
import com.example.p1_ppm.screens.login.student.claseViewModel

import com.google.firebase.auth.FirebaseUser

@Composable
fun Navigation(context: Context, navController: NavHostController = rememberNavController()) {
    val analytics: AnalyticsManager = AnalyticsManager(context)

    val authManager: AuthManager = AuthManager(context)

    val user: FirebaseUser? = authManager.getUsuario()

    //val firestore: FirestoreManager = FirestoreManager(context)

    val raltime: RealtimeManager = RealtimeManager(context)

    val ControllerNav: NavController = NavController(context)

    val viewmodel: claseViewModel = claseViewModel()

    Screen {
        NavHost(
            navController = navController,
            startDestination = if(user == null) Routes.Login.route else Routes.PaginaP.route
        ) {
            composable(Routes.Login.route) {
                Login(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }

            composable(Routes.SignUp.route) {
                SignUp(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.ForgotPassword.route) {
                ForgotPassword(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }

            composable(Routes.PaginaP.route){
                paginaPrincipal_fun(tipo = true, analytics = analytics, auth = authManager, navigation = navController, viewmodel = viewmodel)
            }


        }
    }
}