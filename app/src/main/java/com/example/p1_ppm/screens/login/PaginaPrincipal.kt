package com.example.p1_ppm.screens.login


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.p1_ppm.Managers.AnalyticsManager
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.BottomBarScreen
import com.example.p1_ppm.BottomNavBar_fun
import com.example.p1_ppm.Managers.FirestoreManager
import com.example.p1_ppm.R
import com.example.p1_ppm.navigation.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun paginaPrincipal_fun(tipo:Boolean, analytics: AnalyticsManager, auth: AuthManager, navigation: NavController, firestore: FirestoreManager) {
    val navController = rememberNavController()
    analytics.logScreenView(screenName = Routes.PaginaP.route)
    val user = auth.getUsuario()

    var tipo by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia

    val onLogoutConfirmed: () -> Unit = {
        auth.signOut()
        navigation.navigate(Routes.Login.route){
            popUpTo(Routes.PaginaP.route){
                inclusive = true
            }
        }
    }


    Scaffold(
        topBar = {
            var showmenu by remember{ mutableStateOf(false)}
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(color4))
                            .padding(8.dp)
                    ) {
                        if (user?.photoUrl != null) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(user?.photoUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Imagen",
                                placeholder = painterResource(id = R.drawable.profile),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(40.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.profile),
                                contentDescription = "Foto de perfil por defecto",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            androidx.compose.material.Text(
                                text = if (!user?.displayName.isNullOrEmpty()) "Hola ${user?.displayName}" else "Bienvedi@",
                                fontSize = 20.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            androidx.compose.material.Text(
                                text = if (!user?.email.isNullOrEmpty()) "${user?.email}" else "Anónimo",
                                fontSize = 12.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            showDialog = true
                        },
                        modifier = Modifier
                            .background(Color(color4))
                            .padding(8.dp)
                    ) {
                        Icon(
                            Icons.Outlined.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    }

                    IconButton(onClick = { showmenu =! showmenu },
                        modifier = Modifier
                            .background(Color(color4))
                            .padding(8.dp)) {
                        Icon(
                            Icons.Outlined.Menu,
                            contentDescription = "Cambiar rol"
                        )
                    }
                    DropdownMenu(
                        expanded = showmenu,
                        onDismissRequest = { showmenu = false },
                        modifier = Modifier
                            .background(Color(color4))
                            .padding(8.dp)
                    ) {
                        DropdownMenuItem(onClick = { tipo =!tipo },
                            modifier = Modifier
                                .background(Color(color4))
                                .padding(8.dp)) {
                            Button(onClick = {tipo = !tipo},
                                modifier = Modifier
                                    .background(Color(color4))
                                    .padding(8.dp)) {
                                var estado: String
                                if(tipo == false){
                                    estado = "Tutor"
                                }else{
                                    estado = "Alumno"
                                }
                                Text("Cambiar a $estado")

                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController, tipo)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if (showDialog) {
                LogoutDialog(onConfirmLogout = {
                    onLogoutConfirmed()
                    showDialog = false
                }, onDismiss = { showDialog = false })
            }

            BottomNavBar_fun(navController = navController, firestore = firestore, context = context)
        }

    }
}

@Composable
fun LogoutDialog(onConfirmLogout: () -> Unit, onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { androidx.compose.material.Text("Cerrar sesión") },
        text = { androidx.compose.material.Text("¿Estás seguro que deseas cerrar sesión?") },
        confirmButton = {
            Button(
                onClick = onConfirmLogout
            ){
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ){
                Text("Cancelar")
            }
        }
    )
}



@Composable
fun BottomBar(navController:NavHostController, tipo:Boolean) {
    val screens = remember(tipo) {
        if (tipo) {
            listOf(
                BottomBarScreen.HomeT,
                BottomBarScreen.CalendarioT,
                BottomBarScreen.PerfilT,
            )
        } else {
            listOf(
                BottomBarScreen.HomeA,
                BottomBarScreen.CalendarioA,
                BottomBarScreen.BuscarA,
                BottomBarScreen.PerfilA,
            )

        }
    }

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
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
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
            navController.navigate(screen.ruta){
                popUpTo(navController.graph.id)
                launchSingleTop = true
            }
        }
    )
}