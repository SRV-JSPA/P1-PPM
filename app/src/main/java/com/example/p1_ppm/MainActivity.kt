package com.example.p1_ppm

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.p1_ppm.navigation.Navigation
import com.example.p1_ppm.ui.theme.P1PPmTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P1PPmTheme{
                Navigation(this )
            }
        }
    }
}