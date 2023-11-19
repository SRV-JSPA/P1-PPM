package com.example.p1_ppm.screens.login.teacher

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.Managers.CalendarLogic
import com.example.p1_ppm.Managers.RealtimeManager
import com.example.p1_ppm.Model.Clases
import com.example.p1_ppm.Model.GetEventModel
import com.example.p1_ppm.screens.login.student.calendarioAlumno_fun
import com.example.p1_ppm.ui.theme.P1PPmTheme
import com.google.protobuf.Empty
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.p1_ppm.screens.login.student.TarjetaResultado

class CalendarioTutor {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun calendarioTutor_fun(navController: NavController) {

    val calendario = CalendarLogic(LocalContext.current)
    val eventos = calendario.getDataFromCalendar().observeAsState()

    val daysOfWeek = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado", "Domingo")
    var inputText by remember { mutableStateOf("") }
    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .padding(16.dp),

            ){
            Text(
                text = "Calendario",
                color = Color(color2),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(modifier = Modifier
                .background(Color(color1), shape = RoundedCornerShape(12.dp))){
                Icon(imageVector = Icons.Default.List,
                    contentDescription = "Ver mas",
                    tint = Color(color2),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

        Column(modifier = Modifier
            ,verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            for (day in daysOfWeek) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(color1), shape = RectangleShape),

                    ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Ver Tutorias",
                        tint = Color(color4),
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = day,
                        color = Color(color2),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)

                    )

                }
            }
            if(!eventos.value?.isEmpty()!!){
                LazyColumn {
                    items(eventos.value ?: emptyList()) { tutor ->
                        diaConEvento(tutor)
                    }
                }
            }


        }
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text(text = "Clase - Hora") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun diaConEvento(evento: GetEventModel) {
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    Text(
        text = evento.summary.toString(),
        color = Color(color2),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(8.dp)

    )
}

@Preview
@Composable
fun tutorPreview() {
    P1PPmTheme {
        calendarioAlumno_fun(navController = rememberNavController())
    }
}