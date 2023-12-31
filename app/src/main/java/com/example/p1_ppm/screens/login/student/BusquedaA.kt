package com.example.p1_ppm.screens.login.student

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.p1_ppm.ui.theme.P1PPmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.p1_ppm.Managers.RealtimeManager
import com.example.p1_ppm.Model.Clases
import androidx.compose.runtime.livedata.observeAsState

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

                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel:claseViewModel, realtime: RealtimeManager) {
    var busquedaTexto by remember { mutableStateOf("") }
    val resultados = viewModel.buscarTutorPorNombre(busquedaTexto).observeAsState()
    val res = viewModel.buscarclase(busquedaTexto).observeAsState()

    Column {

        TextField(
            value = busquedaTexto,
            onValueChange = { busquedaTexto = it },
            label = { Text("Buscar") }
        )


        LazyColumn {
            items(resultados.value ?: emptyList()) { tutor ->
                TarjetaResultado(tutor, realtime)
            }

            items(res.value ?: emptyList()) { clase ->
                TarjetaResultado(clase, realtime)
            }
        }
    }
}




@Composable
fun TarjetaResultado(tutor: Clases, realtime: RealtimeManager) {
    var showAsignarClaseDialog by remember { mutableStateOf(false) }

    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia

    if (showAsignarClaseDialog) {
        AsignarClaseDialog(
            clase = tutor,
            onClaseAdded = { clase ->
                realtime.addClaseAS(clase)
                showAsignarClaseDialog = false
            },
            onDismiss = {
                showAsignarClaseDialog = false
            }
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color(color1))
    ) {
        Column(
            modifier = Modifier

                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tutor.nombre,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                IconButton(
                    onClick = {
                              showAsignarClaseDialog = true
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = tutor.nclase,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nota: ${tutor.nota}",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Numero: ${tutor.numero}",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun AsignarClaseDialog(clase: Clases,onClaseAdded: (Clases) -> Unit, onDismiss: () -> Unit,) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Asignar clase") },
        text = { Text("¿Estás seguro que deseas asignarte a esta clase?") },
        confirmButton = {
            Button(
                onClick = {
                    onClaseAdded(clase)
                    onDismiss()
                }
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}


