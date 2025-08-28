package com.example.siaga.ui.screen
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.glance.LocalContext
import androidx.navigation.NavHostController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.ButtonComponent
import com.example.siaga.ui.component.OutlinedButtonComponent
import com.example.siaga.ui.component.OutlinedTextFieldComponent
import com.example.siaga.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


@Composable
fun Login(navController: NavHostController,loginViewModel: AuthViewModel?=null) {
    var loginUiState = loginViewModel?.loginUiState
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
//                    val task: Task<GoogleSignInAccount> =
                }
            }
}
    if(loginUiState?.isSuccess == true) navController.navigate(Screens.Home.screen)
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
                .offset(
                    x = 60.dp,
                    y = 10.dp
                )
                .size(300.dp)
        )
    }
}
    Card(
        modifier = Modifier
            .requiredWidth(width = 400.dp)
            .requiredHeight(height = 500.dp)
            .offset(

                y = 270.dp
            )
            .clip(shape = RoundedCornerShape(75.dp))

            .padding(20.dp),

    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Masuk Sekarang",
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextFieldComponent(value = loginUiState?.email ?: "", label = "Masukan Username") { tx ->
                    loginViewModel?.onEmailChangeLogin(tx)
                }
                OutlinedTextFieldComponent(value = loginUiState?.password ?: "", label = "Masukan Password", type = "Password") { tx ->
                    loginViewModel?.onPasswordChangeLogin(tx)

                }

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
                            navController.navigate(Screens.Forget_Password.screen)
                            // Handle the click event
                            // For example, navigate to a password recovery screen
                        }
                    )


                }
                ButtonComponent(OnClick = {
                    loginViewModel?.loginAction()
                }, label = "Masuk")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Belum punya akun?")
                    Spacer(modifier = Modifier.width(4.dp))
                    ClickableText(
                        text = AnnotatedString("Daftar"),
                        style = TextStyle(color = Color(2, 125, 180, 228)),
                        onClick = {
                            navController.navigate(Screens.Register.screen)
                        }
                    )
                }
//                Button(
//                    onClick = {
////                        startForResult.launch(googleSignInClient?.signInIntent)
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, end = 16.dp),
//                    shape = RoundedCornerShape(6.dp),
////                    colors = ButtonDefaults.buttonColors(
////                        backgroundColor = Color.Black,
////                        contentColor = Color.White
////                    )
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.goggle),
//                        contentDescription = ""
//                    )
//                    Text(text = "Sign in with Google", modifier = Modifier.padding(6.dp))
//                }
//                OutlinedButtonComponent( OnClick = { navController.navigate(Screens.Goggle.screen) }, borderColor = Color(2, 125, 180, 228)){
//                    Icon(
//                        painter = painterResource(id = R.drawable.goggle),  // Assuming this is the Google logo
//                        contentDescription = "Google Logo",
//                        tint = Color.Unspecified,  // Use Color.Unspecified to keep the original logo colors
//                        modifier = Modifier
//                            .size(24.dp)
//                            .padding(end = 8.dp)
//                    )
//
//                    Text(text = "Login dengan Google", color = Color(2, 125, 180, 228))
                }

            }
        }
    }