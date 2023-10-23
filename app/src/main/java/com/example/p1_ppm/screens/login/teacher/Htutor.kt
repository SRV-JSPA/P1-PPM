package com.example.p1_ppm.screens.login.teacher

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
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.ui.theme.P1PPmTheme

class Htutor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview7()
                }
            }
        }
    }
}



@Composable
fun Htutor_fun(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Primera columna con 5 celdas
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CellItem(0, "Clases")
            repeat(4) { index ->
                CellItem(index + 1, null)
            }
        }

        // Segunda columna con 5 celdas
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CellItem(5, "Tutor")
            repeat(4) { index ->
                CellItem(index + 6, null)
            }
        }
    }

    Button(
        onClick = {  },
        modifier = Modifier.padding(vertical = 200.dp, horizontal = 150.dp)

    ) {
        Text(text = "Asignar")
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