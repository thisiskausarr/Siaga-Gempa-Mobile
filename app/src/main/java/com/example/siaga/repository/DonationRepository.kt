package com.example.siaga.repository

import android.util.Log
import com.example.siaga.model.Donation
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

class DonationRepository {
    private val donationCollection = Firebase.firestore.collection("donation")
     suspend fun getAllDonations(onSuccess:(List<Donation>)->Unit) = withContext(Dispatchers.IO) {
        donationCollection.get().addOnSuccessListener {
            val donations = emptyList<Donation>().toMutableList()
            for (d in it){
                val document = d.data
                Log.d("DonationRepository for",document.toString())
                val location= document["location"] as Map<*, *>
                donations += Donation(
                    id=d.id,
                    image = document["image"].toString(),
                    title= document["title"].toString(),
                    location=LatLng(
                        location["latitude"].toString().toDouble(),
                        location["longiture"].toString().toDouble()
                    ),
                    nominal= document["nominal"].toString().toInt(),
                    target= document["target"].toString().toInt()
                )
            }
            onSuccess(donations)
        }
    }
    suspend fun getById(donationId:String,onSuccess: (Donation) -> Unit)= withContext(Dispatchers.IO) {
        donationCollection.document(donationId).get().addOnSuccessListener { document ->
            val location= document?.get("location") as Map<*, *>
            onSuccess.invoke(Donation(image = document["image"].toString(), title = document["title"].toString(), location=LatLng( location["latitude"].toString().toDouble(), location["longiture"].toString().toDouble()),
                nominal= document["nominal"].toString().toInt(), target= document["target"].toString().toInt()))
        }


    }
    suspend fun addDonation(donationId:String,nominal:String)= withContext(Dispatchers.IO){
        donationCollection.document(donationId).get().addOnSuccessListener { document ->
            donationCollection.document(donationId).update("nominal", (document.data?.get("nominal")?:0).toString().toInt()+ nominal.toInt())

        }
    }
}