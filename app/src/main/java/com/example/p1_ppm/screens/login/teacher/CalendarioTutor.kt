package com.example.p1_ppm.screens.login.teacher

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.p1_ppm.Model.GetEventModel
import com.example.p1_ppm.screens.login.student.calendarioAlumno_fun
import com.example.p1_ppm.ui.theme.P1PPmTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarioTutor {
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun calendarioTutor_fun(navController: NavController) {

    val calendario = CalendarLogic(LocalContext.current)
    var eventos by remember{ mutableStateOf(mutableListOf<GetEventModel>())}
    var estado by remember{ mutableStateOf(false)}

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch (Dispatchers.IO){
            eventos = calendario.getDataFromCalendar()
        }
        estado = true
    }


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

        Row(
            modifier = Modifier
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
                    if(estado){
                        LazyColumn {
                            items(eventos?: emptyList()) { tutor ->
                                diaConEvento(tutor,day)
                            }
                        }
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun diaConEvento(evento: GetEventModel,diaCaja:String) {
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    var año = ""+evento.startDate[0]+evento.startDate[1]+evento.startDate[2]+evento.startDate[3]
    var mes = ""+evento.startDate[5]+evento.startDate[6]
    var dia = ""+evento.startDate[8]+evento.startDate[9]
    val currentDate: LocalDate = LocalDate.of(año.toInt(), mes.toInt(), dia.toInt())
    val diaDeSemana = getDiaDeSemana(currentDate.dayOfWeek.toString())

    if(diaDeSemana.equals(diaCaja)){
        Text(
            text = evento.summary.toString(),
            color = Color(color2),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)

        )
    }
}

fun getDiaDeSemana(dayOfWeek: String): Any {
    return when (dayOfWeek) {
        "MONDAY" -> "Lunes"
        "TUESDAY" -> "Martes"
        "WEDNESDAY" -> "Miércoles"
        "THURSDAY" -> "Jueves"
        "FRIDAY" -> "Viernes"
        "SATURDAY" -> "Sábado"
        "SUNDAY" -> "Domingo"
        else -> {}
    }
}


@Preview
@Composable
fun tutorPreview() {
    P1PPmTheme {
        calendarioAlumno_fun(navController = rememberNavController())
    }
}