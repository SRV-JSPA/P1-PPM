package com.example.p1_ppm.screens.login.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.p1_ppm.Model.Clases
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class claseViewModel: ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("clases")

    fun buscarTutorPorNombre(nombre: String): LiveData<List<Clases>> {
        val resultados = MutableLiveData<List<Clases>>()

        // Realiza la consulta en la base de datos
        database.orderByChild("nombre").equalTo(nombre)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val clases = mutableListOf<Clases>()
                    for (tutorSnapshot in snapshot.children) {
                        val tutor = tutorSnapshot.getValue(Clases::class.java)
                        tutor?.let { clases.add(it) }
                    }
                    resultados.value = clases
                }

                override fun onCancelled(error: DatabaseError) {
                    // Manejar el error
                }
            })

        return resultados
    }

    fun buscarclase(Nclase: String): LiveData<List<Clases>> {
        val resultados = MutableLiveData<List<Clases>>()

        // Realiza la consulta en la base de datos
        database.orderByChild("nclase").equalTo(Nclase)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val clases = mutableListOf<Clases>()
                    for (tutorSnapshot in snapshot.children) {
                        val clase = tutorSnapshot.getValue(Clases::class.java)
                        clase?.let { clases.add(it) }
                    }
                    resultados.value = clases
                }

                override fun onCancelled(error: DatabaseError) {
                    // Manejar el error
                }
            })

        return resultados
    }



}