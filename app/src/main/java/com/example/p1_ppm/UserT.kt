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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class UserT : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme
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
fun UsuarioT_fun(navController: NavController) {
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
                    // Puedes agregar la imagen de perfil aquí
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Nombre del Tutor",
                    color = Color(color2)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Descripción del Tutor",
                    color = Color(color2)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cursos que imparte",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(coursesList) { course ->
                CourseItem(course)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto "Calificación"
        Text(
            text = "Calificación",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                // Aquí simplemente estoy usando un círculo para representar la estrella
                // Puedes reemplazar esto con un Icon o una imagen de una estrella
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Yellow, CircleShape)
                )
            }
        }
            // Calificación (puedes usar un RatingBar u otro elemento de tu elección)

        Spacer(modifier = Modifier.height(16.dp))

        // Texto "Comentarios"
        Text(
            text = "Comentarios",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        var comentario by remember { mutableStateOf(TextFieldValue()) }
        BasicTextField(
            value = comentario,
            onValueChange = { comentario = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        )

        // Lista de comentarios (puedes mostrar los comentarios aquí)

    }
}

@Composable
fun CourseItemT(course: String) {
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

val coursesListT = listOf(
    "Curso 1: Introducción a Jetpack Compose",
    "Curso 2: Desarrollo de Aplicaciones Android",
    "Curso 3: Diseño de Interfaces de Usuario",
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    P1PPmTheme {
        UsuarioT_fun(
            navController = rememberNavController()
        )
    }
}