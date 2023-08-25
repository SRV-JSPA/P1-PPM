package com.example.p1_ppm
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class CalendarActivity : AppCompatActivity() {

    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    //Obtenido de https://www.geeksforgeeks.org/calendar-view-app-in-android-with-kotlin/
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendario)
        dateTV = findViewById(R.id.idTVDate)
        calendarView = findViewById(R.id.calendarView)
        calendarView
            .setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->

                    val Date = (dayOfMonth.toString() + "-"
                            + (month + 1) + "-" + year)

                    dateTV.setText(Date)
                })

    }
}