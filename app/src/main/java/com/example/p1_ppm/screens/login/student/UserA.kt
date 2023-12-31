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
import androidx.compose.ui.tooling.preview.Preview
import com.example.p1_ppm.ui.theme.P1PPmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Paint.Align
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter


class UserA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme {
              
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun UsuarioA_fun(navController: NavController) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    PickImageFromGalleryA { uri ->
        selectedImageUri = uri
    }

    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris
    val color2 = android.graphics.Color.parseColor("#4535aa")  // Azul
    val color3 = android.graphics.Color.parseColor("#b05cba")  // Morado
    val color4 = android.graphics.Color.parseColor("#ED639E")  // Fusia

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mi Perfil",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(color3), CircleShape)
                ) {
                    selectedImageUri?.let { uri ->

                        Image(
                            painter = rememberImagePainter(uri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))


            }
        }




    }
}



@Composable
fun PickImageFromGalleryA(onImageSelected: (Uri?) -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val color1 = android.graphics.Color.parseColor("#d6d1f5")  // Gris

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri)
        imageUri = uri
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)

        ){
            Text(
                color = Color(color1),
                text = "Cambiar imagen"
            )
        }
    }
}





