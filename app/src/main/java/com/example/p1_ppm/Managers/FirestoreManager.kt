package com.example.p1_ppm.Managers

import android.content.Context
import com.example.p1_ppm.Model.Clases
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/*
class FirestoreManager(context: Context) {
    private val firestore = FirebaseFirestore.getInstance()

    private val auth = AuthManager(context)
    var userId = auth.getUsuario()?.uid

    suspend fun agregarClase (clases: Clases){
        clases.userId = userId.toString()
        firestore.collection("clases publicadas").add(clases).await()
    }

    suspend fun editarClases (clases: Clases){
        val claseRef = clases.id?.let { firestore.collection("clases publicadas").document(it) }
        claseRef?.set(clases)?.await()
    }

    suspend fun deleteClase (claseId: String ){
        val claseRef = firestore.collection("clases publicadas").document(claseId)
        claseRef.delete().await()
    }

    fun getClasesFlow (): Flow<List<Clases>> = callbackFlow{
        val clasesRef = firestore.collection("clases publicadas").whereEqualTo("userId", userId).orderBy("title")

        val suscription = clasesRef.addSnapshotListener { snapshot, _ ->
            snapshot?.let {querySnapshot ->
                val clases = mutableListOf<Clases>()
                for(document in querySnapshot.documents){
                    val clase = document.toObject(Clases::class.java)
                    clase?.id = document.id
                    clase?.let { clases.add(it) }
                }
                trySend(clases).isSuccess
            }
        }

        awaitClose{suscription.remove()}
    }
}
*/
