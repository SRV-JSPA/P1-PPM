package com.example.p1_ppm.screens.login.student

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.Managers.RealtimeManager
import com.example.p1_ppm.Model.Clases
import com.example.p1_ppm.screens.login.teacher.AddClaseDialog
import com.example.p1_ppm.screens.login.teacher.EditClaseDialog

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
                }
            }
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hestudiante_fun(navController: NavController, realtime: RealtimeManager, authManager: AuthManager) {
    val clases by realtime.getClasesFlowAS().collectAsState(emptyList())
    Scaffold { _  ->
        if(clases.isNotEmpty()) {
            LazyColumn {
                clases.forEach { clase ->
                    item {
                        ClaseItem(clase = clase, realtime = realtime, authManager = authManager)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "No se encontraron \nClases Asignadas",
                    fontSize = 18.sp, fontWeight = FontWeight.Thin, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ClaseItem(clase: Clases, realtime: RealtimeManager, authManager: AuthManager) {
    var showDeleteClaseDialog by remember { mutableStateOf(false) }


    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris

    val onDeleteClaseConfirmed: () -> Unit = {
        realtime.deleteClaseAS(clase.key ?: "")
    }



    if (showDeleteClaseDialog) {
        DeleteClaseDialog(
            onConfirmDelete = {
                onDeleteClaseConfirmed()
                showDeleteClaseDialog = false
            },
            onDismiss = {
                showDeleteClaseDialog = false
            }
        )
    }

    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 0.dp)
            .fillMaxWidth()
            .background(color = Color(color1))
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(3f)) {
                Text(
                    text = clase.nclase,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clase.nombre,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clase.numero,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
            ) {
                IconButton(
                    onClick = {
                        showDeleteClaseDialog = true
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }

        }
    }
}

@Composable
fun DeleteClaseDialog(onConfirmDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar clase") },
        text = { Text("¿Estás seguro que deseas eliminar la clase?") },
        confirmButton = {
            Button(
                onClick = onConfirmDelete
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







