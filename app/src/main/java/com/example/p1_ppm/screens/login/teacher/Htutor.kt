package com.example.p1_ppm.screens.login.teacher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.Managers.RealtimeManager
import com.example.p1_ppm.Model.Clases
import com.example.p1_ppm.ui.theme.P1PPmTheme
import com.google.firebase.database.FirebaseDatabase

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
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Htutor_fun(navController: NavHostController, realtime: RealtimeManager, authManager: AuthManager) {
    var showAddClaseDialog by remember { mutableStateOf(false) }

    val clases by realtime.getClasesFlow().collectAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddClaseDialog = true
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Clase")
            }

            if (showAddClaseDialog) {
                AddClaseDialog(
                    onClaseAdded = { clase ->
                        realtime.addClase(clase)
                        showAddClaseDialog = false
                    },
                    onDialogDismissed = { showAddClaseDialog = false },
                    authManager = authManager,
                )
            }
        }
    ) { _  ->
        if(!clases.isNullOrEmpty()) {
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
                Text(text = "No se encontraron \nClases",
                    fontSize = 18.sp, fontWeight = FontWeight.Thin, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ClaseItem(clase: Clases, realtime: RealtimeManager, authManager: AuthManager) {
    var showDeleteClaseDialog by remember { mutableStateOf(false) }

    var showEditClaseDialog by remember { mutableStateOf(false) }

    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris

    val onDeleteClaseConfirmed: () -> Unit = {
        realtime.deleteClase(clase.key ?: "")
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

    if (showEditClaseDialog) {
        EditClaseDialog(
            clase = clase,
            onClaseUpdated = {

                showEditClaseDialog = false
            },
            onDismiss = {
                showEditClaseDialog = false
            },
            realtime = realtime,
            authManager = authManager
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
                    text = clase.nota,
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
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top,
            ) {
                IconButton(
                    onClick = {
                        showEditClaseDialog = true
                    },
                ) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "Edit icon")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClaseDialog(onClaseAdded: (Clases) -> Unit, onDialogDismissed: () -> Unit, authManager: AuthManager) {
    var Nclase by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var uid = authManager.getUsuario()?.uid

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Agregar Clase") },
        confirmButton = {
            Button(
                onClick = {
                    val newClase = Clases(
                        nclase = Nclase,
                        nota = nota,
                        nombre = nombre,
                        numero = numero,
                        uid = uid.toString())
                    onClaseAdded(newClase)
                    Nclase = ""
                    nota = ""
                    nombre = ""
                    numero = ""
                }
            ) {
                Text(text = "Agregar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDialogDismissed()
                }
            ) {
                Text(text = "Cancelar")
            }
        },
        text = {
            Column {
                TextField(
                    value = Nclase,
                    onValueChange = { Nclase = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre de la clase") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nota,
                    onValueChange = { nota = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nota") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = numero,
                    onValueChange = { numero = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Número telefónico") }
                )
            }
        }
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClaseDialog(
    clase: Clases,
    onClaseUpdated: (Clases) -> Unit,
    onDismiss: () -> Unit,
    realtime:RealtimeManager,
    authManager: AuthManager
) {

    var Nclase by remember { mutableStateOf(clase.nclase) }
    var nota by remember { mutableStateOf(clase.nota) }
    var nombre by remember { mutableStateOf(clase.nombre) }
    var numero by remember { mutableStateOf(clase.numero) }
    var uid = authManager.getUsuario()?.uid

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Editar Clase") },
        confirmButton = {
            Button(
                onClick = {
                    val updatedClase = Clases(
                        nclase = Nclase,
                        nota = nota,
                        nombre = nombre,
                        numero = numero,
                        uid = uid.toString()
                    )
                    realtime.updateClase(clase.key ?: "", updatedClase)
                    onDismiss()
                }
            ) {
                Text(text = "Actualizar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(text = "Cancelar")
            }
        },
        text = {
            Column {
                TextField(
                    value = Nclase,
                    onValueChange = { Nclase = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre de la clase") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nota,
                    onValueChange = { nota = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nota") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = numero,
                    onValueChange = { numero = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Número telefónico") }
                )
            }
        }
    )
}



fun updateClases(uid:String, Nclase: String, nota: String, nombre: String, numero:String){
    val dbRef = FirebaseDatabase.getInstance().getReference("clases").child(uid)
    val Clasesinfo = Clases(Nclase, nota, nombre, numero, uid)
    dbRef.setValue(Clasesinfo)
}