package com.example.p1_ppm.screens.auth


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import kotlinx.coroutines.launch
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.material.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.p1_ppm.Managers.AnalyticsManager
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.Managers.AuthRes
import com.example.p1_ppm.navigation.Routes
import com.example.p1_ppm.screens.login.student.calendarioAlumno_fun
import com.example.p1_ppm.ui.theme.P1PPmTheme
import com.example.p1_ppm.ui.theme.Purple40

import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun SignUp(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController){
    analytics.logScreenView(screenName = Routes.SignUp.route)
    val context = LocalContext.current
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    var email by remember { mutableStateOf("")}
    var contra by remember { mutableStateOf("")}

    val scope = rememberCoroutineScope()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "Crear Cuenta",
            style = TextStyle(fontSize = 40.sp, color = Purple40)
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = { Text(text= "Correo electrónico")},
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {email = it})

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text= "Contraseña")},
            value = contra,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {contra = it})
        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier
            .padding(40.dp, 0.dp, 40.dp, 0.dp )
        ) {
            Button(
                onClick = {
                    scope.launch {
                        signUp(email, contra, auth, analytics, context, navigation)
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(color3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ){
                Text(text = "Registrarse")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        ClickableText(
            text = AnnotatedString("¿Ya tienes cuenta? Inicia sesión"),
            onClick = {
                navigation.popBackStack()
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
}

private suspend fun signUp(email: String, contra: String, auth: AuthManager, analytics: AnalyticsManager, context: Context, navigation: NavController) {
    if(email.isNotEmpty() && contra.isNotEmpty()){
        when(val result = auth.createUserWithEmailAndPassword(email, contra)){
            is AuthRes.Success -> {
                analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
                Toast.makeText(context, "Registro existoso", Toast.LENGTH_SHORT).show()
                navigation.popBackStack()
            }
            is AuthRes.Error -> {
                analytics.logButtonClicked("Error SignUp: ${result.errorMessage}")
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }else{
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}
