package com.example.siaga.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//data class UserProfile(
//    val name: String,
//    val email: String,
//    val language: String,
//    // ... other profile properties
//)
class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        // Fetch user profile data from your data source (e.g., database, API)
        viewModelScope.launch {
            // Replace this with your actual data fetching logic
            val fetchedUserProfile =UserProfile(
                name = "John Doe",
                email = "john.doe@example.com"
            )
            _userProfile.value = fetchedUserProfile
        }
    }

    fun updateProfile(updatedProfile: UserProfile) {
        viewModelScope.launch {
            // Update the user profile in your data source
            // ...
            _userProfile.value = updatedProfile
        }
    }
}