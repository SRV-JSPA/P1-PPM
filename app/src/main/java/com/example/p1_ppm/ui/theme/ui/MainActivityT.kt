package com.example.p1_ppm.ui.theme.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p1_ppm.R


class MainActivityT : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun vistaTutores() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tutor")

        val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
        Image(
            painter = image,
            contentDescription = "Imagen",
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.primary)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(5) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(7) { columnIndex ->
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondary)
                                .size(40.dp)
                        ) {
                            Text(
                                text = "Dia ${rowIndex * 7 + columnIndex}",

                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }

        Text(
            text = "Calificación",

        )
        Text(
            text = "Descripción",

        )

        Button(
            onClick = { /* Acción del botón */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Asignar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previstas() {
    vistaTutores()
}






