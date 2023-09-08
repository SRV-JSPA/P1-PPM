package com.example.p1_ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.ui.theme.P1PPmTheme

class Isesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme


            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sesion(usuarios: String, navController: NavController) {
    var pantallalog by remember { mutableStateOf(0) }


    var info = usuarios.split("_")
    var usuarioF = arrayListOf<String>()
    var contraF = arrayListOf<String>()
    var tipoF = arrayListOf<Boolean>()

    for(item in info){
        usuarioF.add( item.split(",")[0])
        contraF.add( item.split(",")[1])
        tipoF.add( item.split(",")[2].toBoolean())
    }


    var usuarioN by remember { mutableStateOf("") }
    var contraN by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Surface(

        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar sesión",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = usuarioN,
            onValueChange = { usuarioN = it },
            label = { Text(text = "Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = contraN,
            onValueChange = { contraN = it },
            label = { Text(text = "Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (usuarioF != null) {

                        if (usuarioF.contains(usuarioN)) {
                            var tem = usuarioF.indexOf(usuarioN)
                            if(usuarioN.equals(usuarioF.get(tem))&& contraN.equals(contraF.get(tem))){
                                navController.navigate(route = Screens.PaginaP.passTipo(
                                    tipo = tipoF.get(tem)
                                ))
                                error = false
                            }else{
                                error = true
                            }
                        } else {

                            error = true
                        }

                }


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar sesión")
        }


        if (error) {
            Text(
                text = "Usuario o contraseña incorrecta",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }


    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    P1PPmTheme {
        sesion(
            "Juan,123,false", navController = rememberNavController()
        )
    }
}