package com.example.siaga.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siaga.model.Donation
import com.example.siaga.repository.DonationRepository
import kotlinx.coroutines.launch

class DonationViewModel(private val repository:DonationRepository= DonationRepository()):ViewModel() {

    var donations by mutableStateOf(emptyList<Donation>())
    var donationUiState by mutableStateOf(donationUiState())
    var donation by mutableStateOf(Donation())

    fun onNominalChange(value:String){
        if(value.isEmpty()) return

        donationUiState = donationUiState.copy(nominal=value)
    }

 fun getAllDonations()=viewModelScope.launch {
     repository.getAllDonations(onSuccess = {
        donations = it

     })
     }
    fun getDonationById(donationId: String)=viewModelScope.launch{
         repository.getById(donationId, onSuccess = {
             donation= it
         })
    }
    fun addDonation(donationId:String)=viewModelScope.launch {
        repository.addDonation(donationId,donationUiState.nominal)
    }
}
data class donationUiState(
    val nominal:String="",
)
