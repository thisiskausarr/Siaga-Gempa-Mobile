package com.example.siaga.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siaga.model.User
import com.example.siaga.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class   AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) :ViewModel(){

    val hasUser:Boolean
        get() = repository.hasUser()
    var loginUiState by mutableStateOf(LoginUiState())
        private set
    var registerUiState by mutableStateOf(RegisterUiState())
    var currentUser by mutableStateOf(UserData())
    fun onEmailChangeLogin(email:String) { loginUiState = loginUiState.copy(email=email)}
    fun onPasswordChangeLogin(password:String) { loginUiState = loginUiState.copy(password =password)}
//    register
    fun onEmailRegisterChange(email:String) {registerUiState = registerUiState.copy(email =email)}
    fun onPasswordRegisterChange(password:String) { registerUiState = registerUiState.copy(password=password)}
    fun onUsernameRegisterChange(username: String){registerUiState = registerUiState.copy(username=username)}
    fun onConfirmPasswordRegisterChange(confirmPassword: String){registerUiState = registerUiState.copy(confirmPassword=confirmPassword)}
    fun getCurremtUser()= viewModelScope.launch {
        repository.getCurrentUser(){it->
            if(it!=null) currentUser = currentUser.copy(data = it)
        }
    }
    fun validateRegister()=
        registerUiState.email.isNotBlank() &&
                registerUiState.password.isNotBlank() &&
                registerUiState.confirmPassword.isNotBlank() &&
                registerUiState.username.isNotBlank()
    fun validateLogin()=loginUiState.email.isNotBlank() && loginUiState.password.isNotBlank()
    fun loginAction() = viewModelScope.launch {
        try {
            if (!validateLogin()) throw IllegalArgumentException("Please Fill All Inputs")
            else repository.signIn(loginUiState.email,loginUiState.password){
                isComplated->
//                if(isComplated) Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                loginUiState = loginUiState.copy(isSuccess = isComplated)

            }
        }catch (e:Exception){

//            Toast.makeText(context,e.message,Toast.LENGTH_LONG)
            Log.d("Error",e.message.toString())
        }
    }
    fun registerAction(context: Context)= viewModelScope.launch{
        try {
            Log.d("Action","Email: ${registerUiState.email}, Username: ${registerUiState.username}, password: ${registerUiState.password}, confirm password: ${registerUiState.confirmPassword}")
            Log.d("Action Password",(registerUiState.password != registerUiState.confirmPassword).toString())
            if(!validateRegister()) throw IllegalArgumentException("Please Fill All Inputs")
            else if (registerUiState.password != registerUiState.confirmPassword) throw IllegalArgumentException("Password And Confirm Password Isn't Match")
            else
                repository.createUser(email= registerUiState.email, username = registerUiState.username,password =registerUiState.password){
                    isComplated->
                    if(isComplated) Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                    registerUiState = registerUiState.copy(isSuccess = true)
                    Log.d("isComplated",isComplated.toString())
                }

        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_LONG)
            Log.d("Error",e.message.toString())
        }
    }
    fun logoutAction()= viewModelScope.launch {
        repository.signOut()
    }
}


data class LoginUiState(
    val email:String = "",
    val password:String= "",
    val isSuccess:Boolean=false,
    val errorMessage:String? = null
)
data class RegisterUiState(
    val username:String="",
    val email:String = "",
    val password:String= "",
    val confirmPassword:String="",
    val isSuccess: Boolean=false
)
data class UserProfile(
    val name: String,
    val email: String,
    // Add other profile properties as needed
)
data class UserData(
    val data:User? =null
)