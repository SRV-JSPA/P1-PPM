package com.example.p1_ppm.screens.auth



import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.p1_ppm.Managers.AnalyticsManager
import com.example.p1_ppm.Managers.AuthManager
import com.example.p1_ppm.Managers.AuthRes
import com.example.p1_ppm.R
import com.example.p1_ppm.navigation.Routes
import com.example.p1_ppm.ui.theme.Purple40
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController){

    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia

    var email by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()){ result ->
        when(val account = auth.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))){
            is AuthRes.Success ->{
                val credential = GoogleAuthProvider.getCredential(account?.data?.idToken, null)
                scope.launch {
                    val fireUser = auth.signInWithGoogleCredential(credential)
                    if(fireUser != null){
                        Toast.makeText(context, "Bienvenid@", Toast.LENGTH_SHORT).show()
                        navigation.navigate(Routes.Home.route){
                            popUpTo(Routes.Login.route){
                                inclusive = true
                            }
                        }
                    }
                }
            }
            is AuthRes.Error ->{
                analytics.logError("Error SignIn: ${account.errorMessage}")
                Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("¿No tienes una cuenta? Regístrate"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp),
            onClick = {
                navigation.navigate(Routes.SignUp.route)
                analytics.logButtonClicked("Click: No tienes una cuenta? Regístrate")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Tutores UVG",
            style = TextStyle(fontSize = 30.sp))
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = "Correo electrónico") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = { Text(text = "Contraseña") },
            value = contra,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { contra = it })
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    scope.launch {
                        emailPassSignIn(email, contra, auth, analytics, context, navigation)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(color3)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Iniciar Sesión".uppercase())
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("¿Olvidaste tu contraseña?"),
            onClick = {
                navigation.navigate(Routes.ForgotPassword.route){
                    popUpTo(Routes.Login.route){inclusive = true}
                }
                analytics.logButtonClicked("Click: ¿Olvidaste tu contraseña?")
            },
            style = TextStyle(
                fontSize =  14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )

        Spacer(modifier = Modifier.height(25.dp))
        Text(text= "-------- o --------", style = TextStyle(color = Color.Gray))
        Spacer(modifier = Modifier.height(25.dp))
        SocialMediaButton(
            onClick = {
                auth.signInWithGoogle(googleSignInLauncher, navigation)
            },
            text = "Continuar con Google",
            icon = R.drawable.ic_google,
            color = Color.White
        )
    }
}

private suspend fun emailPassSignIn(email: String, password: String, auth: AuthManager, analytics: AnalyticsManager, context: Context, navigation: NavController) {
    if(email.isNotEmpty() && password.isNotEmpty()) {
        when (val result = auth.signInWithEmailAndPassword(email, password)) {
            is AuthRes.Success -> {
                analytics.logButtonClicked("Click: Iniciar sesión correo & contraseña")
                navigation.navigate(Routes.PaginaP.route) {
                    popUpTo(Routes.Login.route) {
                        inclusive = true
                    }
                }
            }

            is AuthRes.Error -> {
                analytics.logButtonClicked("Error SignUp: ${result.errorMessage}")
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    } else {
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialMediaButton(onClick: () -> Unit, text: String, icon: Int, color: Color, ) {
    var click by remember { mutableStateOf(false) }
    Surface(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable { click = !click },
        shape = RoundedCornerShape(50),
        border = BorderStroke(width = 1.dp, color = if(icon == R.drawable.ic_incognito) color else Color.Gray),
        color = color
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.size(24.dp),
                contentDescription = text,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$text", color = if(icon == R.drawable.ic_incognito) Color.White else Color.Black)
            click = true
        }
    }
}