package com.example.p1_ppm.screens.login.teacher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.ui.theme.P1PPmTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Htutor_fun(navController: NavController) {
    var showAddClassDialog by remember{ mutableStateOf(false)}
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddClassDialog = true
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar clases")
            }

            if(showAddClassDialog){

            }
        }
    ){

    }
}

@Composable
fun CellItem(index: Int, text: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), // Tamaño de cada celda

    ) {
        text?.let {
            Text(
                text = it,
                modifier = Modifier.padding(4.dp), // Espaciado más pequeño
                style = TextStyle(fontSize = 16.sp) // Tamaño de texto más pequeño
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    P1PPmTheme {
        Htutor_fun(navController = rememberNavController())
    }
}