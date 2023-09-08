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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BusquedaA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview4()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen() {
    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia
    var searchText by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
                // Aquí puedes realizar la búsqueda con el nuevo texto
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide() // Oculta el teclado al presionar "Buscar"
                    // Aquí puedes realizar la búsqueda con el texto actual
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Resultados de la búsqueda para: $searchText",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    P1PPmTheme {
        SearchScreen()

    }
}