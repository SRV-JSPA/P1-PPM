package com.example.p1_ppm

sealed class Screens(val ruta:String){
    object Home: Screens(ruta = "home")
    object SignIn: Screens(ruta = "sign_in")
    object LogIn: Screens(ruta = "log_in")
    object Test: Screens(ruta = "test")
}
