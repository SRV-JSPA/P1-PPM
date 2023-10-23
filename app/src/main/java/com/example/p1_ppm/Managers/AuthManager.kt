package com.example.p1_ppm.Managers

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.p1_ppm.R
import com.example.p1_ppm.navigation.Routes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential

import com.google.android.gms.auth.api.identity.Identity


import java.lang.Exception


sealed class AuthRes<out T>{
    data class Success<T>(val data: T): AuthRes<T>()
    data class Error(val errorMessage: String): AuthRes<Nothing>()
}

class AuthManager (private val context: Context){
    private val auth: FirebaseAuth by lazy {Firebase.auth}
    private val signInClient = Identity.getSignInClient(context)



    suspend fun createUserWithEmailAndPassword(email: String, contra: String): AuthRes<FirebaseUser?> {
        return try{
            val authResult = auth.createUserWithEmailAndPassword(email, contra).await()
            AuthRes.Success(authResult.user)
        } catch( e: Exception){
            AuthRes.Error(e.message ?: "Error al crear el usuario")
        }
    }

    fun signOut(){
        auth.signOut()
        signInClient.signOut()
    }

    fun getUsuario(): FirebaseUser?{
        return auth.currentUser
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch(e: Exception) {
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    suspend fun resetContra (email :String): AuthRes<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Success(Unit)
        } catch (e:Exception){
            AuthRes.Error(e.message ?: "Error al restablecer la constraseña")
        }
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount> {
        return try{
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        }catch (e:Exception){
            AuthRes.Error(e.message ?: "Google sign-in failed")
        }
    }

    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser> {
        return try{
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sign in with google failed")
        } catch (e:Exception){
            AuthRes.Error(e.message ?: "Sign in with google failed")
        }
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>, navigation: NavController){
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)

        }
    }

