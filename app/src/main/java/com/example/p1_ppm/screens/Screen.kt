package com.example.p1_ppm.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.p1_ppm.ui.theme.P1PPmTheme


@Composable
fun Screen(content: @Composable () -> Unit){
    P1PPmTheme{
        Surface( modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ){
            content()
        }
    }
}