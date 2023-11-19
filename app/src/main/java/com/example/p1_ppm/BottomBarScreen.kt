package com.example.p1_ppm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val ruta:String,
    val titulo:String,
    val icono:ImageVector
){
    object HomeA : BottomBarScreen(
        ruta = "homeA",
        titulo = "Home",
        icono = Icons.Default.Home
    )
    object CalendarioA : BottomBarScreen(
        ruta = "Calendario",
        titulo = "Calendario",
        icono = Icons.Default.DateRange
    )
    object BuscarA : BottomBarScreen(
        ruta = "Buscar",
        titulo = "Buscar",
        icono = Icons.Default.Search
    )
    object PerfilA : BottomBarScreen(
        ruta = "perfilA",
        titulo = "Perfil",
        icono = Icons.Default.Person
    )

    object HomeT : BottomBarScreen(
        ruta = "home",
        titulo = "Home",
        icono = Icons.Default.Home
    )
    object CalendarioT : BottomBarScreen(
        ruta = "Calendario",
        titulo = "Calendario",
        icono = Icons.Default.DateRange
    )

    object PerfilT : BottomBarScreen(
        ruta = "perfil",
        titulo = "Perfil",
        icono = Icons.Default.Person
    )


}
