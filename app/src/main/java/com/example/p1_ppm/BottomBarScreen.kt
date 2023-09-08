package com.example.p1_ppm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val ruta:String,
    val titulo:String,
    val icono:ImageVector
){
    object Home : BottomBarScreen(
        ruta = "home",
        titulo = "home",
        icono = Icons.Default.Home
    )
    object Calendario : BottomBarScreen(
        ruta = "Calendario",
        titulo = "Calendario",
        icono = Icons.Default.DateRange
    )
    object Perfil : BottomBarScreen(
        ruta = "perfil",
        titulo = "perfil",
        icono = Icons.Default.Person
    )

}
