package com.example.p1_ppm

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun paginaPrincipal_fun() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {BottomBar(navController = navController)}
    ){
        BottomNavBar_fun(navController = navController)
    }
}

@Composable
fun BottomBar(navController:NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Calendario,
        BottomBarScreen.Perfil
    )
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia
    val navStackBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackStackEntry?.destination

    NavigationBar(
        containerColor = Color(color4)){
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen:BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.titulo)
        },
        icon = {
            Icon(imageVector = screen.icono , contentDescription = "Icono navegación")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.ruta
        } == true,
        onClick = {
            navController.navigate(screen.ruta)
        },
    )
}