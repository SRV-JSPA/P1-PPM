package com.example.p1_ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.ui.theme.P1PPmTheme

class Inicio: ComponentActivity(){
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                navController = rememberNavController()
                setupNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun Inicio_Screen(navController: NavController) {

    var usuarios by remember { mutableStateOf("Juan,123,true_Andres,456,false")}



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = {
                navController.navigate(route = Screens.LogIn.passUserAndPassword(
                    user = usuarios
                ))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar sesión")
        }
        Button(
            onClick = {
                navController.navigate(route = Screens.SignIn.passUserAndPasswordSignIn(
                    user = usuarios
                ))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear Usuario")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    P1PPmTheme {
        Inicio_Screen(navController = rememberNavController())
    }
}