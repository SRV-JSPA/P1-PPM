package com.example.p1_ppm
const val USUARIO_ARGUMENT_KEY1 = "usernames"
const val SIGNIN_ARGUMENT_KEY1 = "usernamesS"
const val PAGINAP_ARGUMENT_KEY1 = "tipo"
sealed class Screens(val ruta:String){
    object Home: Screens(ruta = "home")
    object SignIn: Screens(ruta = "sign_in/{$SIGNIN_ARGUMENT_KEY1}"){
        fun passUserAndPasswordSignIn(
            user:String
        ):String{
            return "sign_in/$user"
        }
    }
    object LogIn: Screens(ruta = "log_in/{$USUARIO_ARGUMENT_KEY1}"){
        fun passUserAndPassword(
            user:String
        ):String{
            return "log_in/$user"
        }
    }
    object PaginaP: Screens(ruta = "pagina_principal/{$PAGINAP_ARGUMENT_KEY1}"){
        fun passTipo(
            tipo:Boolean
        ):String{
            return "pagina_principal/$tipo"
        }
    }
}
