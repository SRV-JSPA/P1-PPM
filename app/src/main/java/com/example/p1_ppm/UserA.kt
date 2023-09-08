package com.example.p1_ppm

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
import com.example.p1_ppm.ui.theme.P1PPmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class UserA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview5()
                }
            }
        }
    }
}

@Composable
fun UserProfileScreen() {
    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Mi Perfil",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Círculo con nombre y descripción
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(color3), CircleShape)
                ) {
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Nombre deL Alumno",
                    color = Color(color2)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Descripción del Alumno",
                    color = Color(color2)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto "Cursos tomados"
        Text(
            text = "Cursos tomados",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lista de cursos tomados
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(coursesList) { course ->
                CourseItem(course)
            }
        }
    }
}

@Composable
fun CourseItem(course: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = course,
            modifier = Modifier.padding(16.dp)
        )
    }
}

val coursesList = listOf(
    "Curso 1: Introducción a Jetpack Compose",
    "Curso 2: Desarrollo de Aplicaciones Android",
    "Curso 3: Diseño de Interfaces de Usuario",
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    P1PPmTheme {
        UserProfileScreen()
    }
}