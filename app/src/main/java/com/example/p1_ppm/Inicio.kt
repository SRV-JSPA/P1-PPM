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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.ui.theme.P1PPmTheme

class Inicio: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                navController = rememberNavController()
                setupNavGraph(navController = navController)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Inicio_Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar sesión")
        }
        Button(
            onClick = {

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
        Inicio_Screen()
    }
}