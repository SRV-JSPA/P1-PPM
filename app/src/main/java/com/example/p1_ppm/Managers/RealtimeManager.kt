package com.example.p1_ppm.Managers


import android.content.Context
import com.example.p1_ppm.Model.Clases
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RealtimeManager(context: Context) {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("clases")
    private val authManager = AuthManager(context)

    fun addClase(clase: Clases) {
        val key = databaseReference.push().key
        if (key != null) {
            databaseReference.child(key).setValue(clase)
        }
    }

    fun deleteClase(claseId: String) {
        databaseReference.child(claseId).removeValue()
    }

    fun updateClase(claseId: String, updateClase: Any?) {
        databaseReference.child(claseId).setValue(updateClase)
    }

    fun getClasesFlow(): Flow<List<Clases>> {
        val idFilter = authManager.getUsuario()?.uid
        val flow = callbackFlow {
            val listener = databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val clases = snapshot.children.mapNotNull { snapshot ->
                        val contact = snapshot.getValue(Clases::class.java)
                        snapshot.key?.let { contact?.copy(key = it) }
                    }
                    trySend(clases.filter { it.uid == idFilter }).isSuccess
                }
                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose { databaseReference.removeEventListener(listener) }
        }
        return flow
    }
}