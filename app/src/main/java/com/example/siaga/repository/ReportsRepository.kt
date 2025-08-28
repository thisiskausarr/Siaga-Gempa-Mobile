package com.example.siaga.repository

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class Report(
    val location: LatLng,
    val userId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "pending",
    val localId:Long
)
class ReportsRepository {
    private val reportsCollection: CollectionReference =  Firebase.firestore.collection("reports")
    suspend fun  createReport(location:LatLng,reported_time:Long,onComplate:(Boolean,String?)->Unit)= withContext(Dispatchers.IO){
        val authRepository:AuthRepository= AuthRepository()
        val userId=authRepository.getUserId()
        val data = Report(location=location,userId=userId,localId=reported_time)
        reportsCollection.add(data).addOnSuccessListener {documentReference ->
            onComplate(true,documentReference.id)
        }.addOnFailureListener {
            onComplate(false,"")
        }

    }
    suspend fun updateReportStatus(reportId:Long,status:String)= withContext(Dispatchers.IO){
        val data = hashMapOf("status" to status)
        reportsCollection.whereEqualTo("localId",reportId).get().addOnSuccessListener { documents->
            val document= documents.first()

            reportsCollection.document(document.id).update(data as Map<String, Any>)


        }
    }
}