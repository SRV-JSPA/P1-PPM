package com.example.p1_ppm.Managers


import android.content.Context
import com.example.p1_ppm.Model.Clases
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RealtimeManager(context: Context) {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("clases")
    private val authManager = AuthManager(context)


    private val dReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("clasesAS")






    fun addClase(clase: Clases) {
        val key = databaseReference.push().key
        if (key != null) {
            databaseReference.child(key).setValue(clase)
        }
    }



    fun deleteClase(claseId: String, clase: Clases) {
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

    fun addClaseAS(clase: Clases ) {
        val key = databaseReference.push().key
        if (key != null) {
            dReference.child(key).setValue(clase)
        }
    }

    fun deleteClaseAS(claseId: String) {
        dReference.child(claseId).removeValue()
    }

    fun getClasesFlowAS(): Flow<List<Clases>> {
        val idFilter = authManager.getUsuario()?.uid
        val flow = callbackFlow {
            val listener = dReference.addValueEventListener(object : ValueEventListener {
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
            awaitClose { dReference.removeEventListener(listener) }
        }
        return flow
    }


}
