package com.example.p1_ppm.screens.login.teacher

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.p1_ppm.Managers.FirestoreManager
import com.example.p1_ppm.Model.Clases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Htutor_fun(navController: NavController, firestore: FirestoreManager) {
    var showAddClassDialog by remember{ mutableStateOf(false)}

    val clases by firestore.getClasesFlow().collectAsState(emptyList())

    val scope = rememberCoroutineScope()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddClassDialog = true
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar clase")
            }
            if(showAddClassDialog){
                AddClaseDialog(
                    onClaseAdded = {clases ->
                        scope.launch {
                            firestore.agregarClase(clases)
                        }
                        showAddClassDialog = false
                    },
                    onDialogDismissed = { showAddClassDialog = false},
                )
            }
        }
    ){
        if(!clases.isNullOrEmpty()){
            LazyColumn{
                clases.forEach{
                    item {
                        ClaseItem(clase = it, firestore = firestore)
                    }
                }
            }
        } else{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = null, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "No se encontraron \nClases",
                    fontSize = 18.sp, fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ClaseItem(clase: Clases, firestore: FirestoreManager) {
    var showDeleteNoteDialog by remember { mutableStateOf(false) }

    val onDeleteNoteConfirmed: () -> Unit = {
        CoroutineScope(Dispatchers.Default).launch {
            firestore.deleteClase(clase.id ?: "")
        }
    }

    if (showDeleteNoteDialog) {
        DeleteClaseDialog(
            onConfirmDelete = {
                onDeleteNoteConfirmed()
                showDeleteNoteDialog = false
            },
            onDismiss = {
                showDeleteNoteDialog = false
            }
        )
    }

    Card(
        modifier = Modifier.padding(6.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text = clase.Nclase,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = clase.nota,
                fontWeight = FontWeight.Thin,
                fontSize = 13.sp,
                lineHeight = 15.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = clase.nombre,
                fontWeight = FontWeight.Thin,
                fontSize = 13.sp,
                lineHeight = 15.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = clase.numero,
                fontWeight = FontWeight.Thin,
                fontSize = 13.sp,
                lineHeight = 15.sp)
            IconButton(
                onClick = { showDeleteNoteDialog = true },
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClaseDialog(onClaseAdded: (Clases) -> Unit, onDialogDismissed: () -> Unit) {
    var nClase by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Agregar Clase") },
        confirmButton = {
            Button(
                onClick = {
                    val NClase = Clases(
                        Nclase = nClase,
                        nota = nota,
                        nombre = nombre,
                        numero = numero)
                    onClaseAdded(NClase)
                    nClase = ""
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
                    value = nClase,
                    onValueChange = { nClase = it},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre de la clase")}
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nota,
                    onValueChange = { nota = it},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nota obtenida en esa clase")}
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nombre del tutor")}
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = numero,
                    onValueChange = { numero = it},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    label = { Text(text = "Contacto del tutor")}
                )
            }
        }
    )
}

@Composable
fun DeleteClaseDialog(onConfirmDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar Clase") },
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