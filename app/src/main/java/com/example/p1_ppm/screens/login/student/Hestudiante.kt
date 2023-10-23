package com.example.p1_ppm.screens.login.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.screens.login.teacher.CellItem
import com.example.p1_ppm.ui.theme.P1PPmTheme

class Hestudiante : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview8()
                }
            }
        }
    }
}



@Composable
fun Hestudiante_fun(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

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


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CellItem(5, "Alumno")
            repeat(4) { index ->
                CellItem(index + 6, null)
            }
        }
    }


}

@Composable
fun tablaE(index: Int, text: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),

    ) {
        text?.let {
            Text(
                text = it,
                modifier = Modifier.padding(4.dp),
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    P1PPmTheme {
        Hestudiante_fun(navController = rememberNavController())
    }
}