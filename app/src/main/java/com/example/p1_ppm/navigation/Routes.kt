package com.example.p1_ppm.navigation

sealed class Routes(val route:String) {
    object Login: Routes("Login")
    object Home: Routes("Home")
    object SignUp: Routes("SignUp")
    object ForgotPassword: Routes("ForgotPassword")
    object PaginaP: Routes("PaginaP")
    object HAlumno: Routes("HAlumno")
}