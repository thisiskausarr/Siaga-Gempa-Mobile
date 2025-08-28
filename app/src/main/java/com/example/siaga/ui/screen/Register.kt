package com.example.siaga.ui.screen

import android.content.Context
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.viewmodel.AuthViewModel

@Composable
fun Register(navController: NavController,registerViewModel:AuthViewModel?=null) {
    var registerUiState = registerViewModel?.registerUiState

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    if(registerUiState?.isSuccess ===true){
        navController.navigate(Screens.Login.screen)
    }

    Box(modifier = Modifier.background(color = Color(0, 0, 0, 150))) {
        Box(
            Modifier
                .requiredHeight(height = 400.dp)
                .requiredWidth(width = 428.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(color = Color(2, 125, 180, 228))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 60.dp, y = 10.dp)
                    .size(300.dp)
            )
        }
    }
    Card(
        modifier = Modifier
            .requiredWidth(width = 400.dp)
            .requiredHeight(height = 600.dp)
            .offset(y = 70.dp)
            .clip(shape = RoundedCornerShape(75.dp))
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Daftar Sekarang",
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = registerUiState?.username ?:"",
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(2, 125, 180, 228)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Masukkan Username") },
                    keyboardActions = KeyboardActions.Default,
                    onValueChange = {
                        registerViewModel?.onUsernameRegisterChange(it)
                    }
                )
                OutlinedTextField(
                    value = registerUiState?.email ?: "",
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(2, 125, 180, 228)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Masukkan Email") },
                    keyboardActions = KeyboardActions.Default,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {
                        registerViewModel?.onEmailRegisterChange(it)
                    }
                )
                OutlinedTextField(
                    value = registerUiState?.password ?:"",
                    onValueChange = {
                        registerViewModel?.onPasswordRegisterChange(it)
                    },
                    label = { Text("Masukkan Password") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(2, 125, 180, 228)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    shape = RoundedCornerShape(20),
                    placeholder = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Lock
                        else Icons.Filled.Face

                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    }
                )
                OutlinedTextField(
                    value = registerUiState?.confirmPassword ?: "",
                    onValueChange = {
                                    registerViewModel?.onConfirmPasswordRegisterChange(it)
                                    },
                    label = { Text("Ulangi Password") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(2, 125, 180, 228)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    shape = RoundedCornerShape(20),
                    placeholder = { Text("Ulangi Password") },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible)
                            Icons.Filled.Lock
                        else Icons.Filled.Face

                        val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ClickableText(
                        text = AnnotatedString("Lupa Kata Sandi?"),
                        style = TextStyle(color = Color(2, 125, 180, 228)),
                        onClick = {
                            // Handle the click event for password recovery
                        }
                    )
                }
                Button(
                    onClick = {
                        registerViewModel?.registerAction(context)
//                        navController.navigate(Screens.Login.screen)
                              },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(2, 125, 180, 228)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Text(text = "Daftar", color = Color.White)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sudah punya akun?")
                    Spacer(modifier = Modifier.width(4.dp))
                    ClickableText(
                        text = AnnotatedString("Login"),
                        style = TextStyle(color = Color(2, 125, 180, 228)),
                        onClick = {
                            navController.navigate(Screens.Login.screen)
                        }
                    )
                }

            }
        }
    }
}
