package com.example.siaga.repository

import android.util.Log
import com.example.siaga.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    var currentUser: User? = null
        get() {
            var authUser:User? = null
             userCollection.whereEqualTo("auth_id",Firebase.auth.currentUser?.uid.toString())
                 .get()
                 .addOnSuccessListener { documents->
                     val data = documents.first().data

                     authUser = User(data["auth_id"],data["username"],data["email"])
                 }

            return authUser
        }
    suspend fun getCurrentUser(onComplate: (User?) -> Unit)=withContext(Dispatchers.IO){
        userCollection.whereEqualTo("auth_id",Firebase.auth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener { documents->
                if(documents.size() == 0) onComplate(null)
                else{

                val data = documents.first().data
                Log.d("AuthRepository","${data}")
                onComplate(User(data["auth_id"],data["username"],data["email"]))
                }

            }

    }
    fun hasUser():Boolean = Firebase.auth.currentUser != null
    fun getUserId ():String = Firebase.auth.currentUser?.uid.orEmpty()
    val userCollection = Firebase.firestore.collection("users")
    suspend fun  createUser(
        username:String,
        email:String,
        password:String,
        onComplate:(Boolean)->Unit
    )= withContext(Dispatchers.IO){
        Log.d("Create User","${username},${email},${password}")
        Firebase.auth
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    var userData : User = User(Firebase.auth.currentUser?.uid.toString(),username,email)
                    userCollection.add(userData)
                    onComplate.invoke(true)
                }
                else onComplate.invoke(false)
            }.await()
    }
    suspend fun signIn(email: String,password: String,onComplate: (Boolean) -> Unit)= withContext(Dispatchers.IO){
        Firebase.auth.signInWithEmailAndPassword(email,password ).addOnCompleteListener {
            if(it.isSuccessful) onComplate.invoke(true)
            else onComplate.invoke(false)
        }
    }
    suspend fun signOut() = withContext(Dispatchers.IO){
        Firebase.auth.signOut()
    }
}