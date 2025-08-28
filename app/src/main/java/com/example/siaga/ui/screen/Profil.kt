package com.example.siaga.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.viewmodel.AuthViewModel
import com.example.siaga.viewmodel.LocationViewModel
import com.example.siaga.viewmodel.ProfileViewModel

@Composable
fun Profil(navController: NavHostController, profileViewModel: ProfileViewModel) {
    val userProfile by profileViewModel.userProfile.collectAsState()
    val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
    authViewModel.getCurremtUser()
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        // User Name and Email
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(id = R.drawable.human),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .height(121.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = authViewModel.currentUser.data?.username.toString() ?: "",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            Text(
                text = authViewModel.currentUser.data?.email.toString()?:"",
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Edit Profile Button
        Button(
            onClick = {
                // Navigate to Edit Profile Screen
                navController.navigate("editProfile")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Edit Profile",
                color = Color.White,
                style = TextStyle(
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Password Change Section
        Button(
            onClick = {
                // Navigate to Change Password Screen
                navController.navigate("changePassword")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Ganti Password",
                color = Color.White,
                style = TextStyle(
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Pusat Bantuan Section
        Button(
            onClick = {
                // Navigate to Pusat Bantuan Screen
                navController.navigate("pusatBantuan")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Pusat Bantuan",
                color = Color.White,
                style = TextStyle(
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Hubungi Kami Section
        Button(
            onClick = {
                // Navigate to Hubungi Kami Screen
                navController.navigate("hubungiKami")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Hubungi Kami",
                color = Color.White,
                style = TextStyle(
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Navigate to Hubungi Kami Screen
                authViewModel.logoutAction()
                navController.navigate(Screens.Login.screen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Keluar",
                color = Color.White,
                style = TextStyle(
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        // Bahasa Section
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.call),
                    contentDescription = "globe2",modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Bahasa",
                    color = Color(0xff181818),
                    style = TextStyle(
                        fontSize = 13.sp
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                BahasaID()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // ... other buttons
    }
}

@Composable
fun BahasaID(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .requiredWidth(width = 66.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color(0xfff2f2f2))
            .padding(all = 2.dp)
    ) {
        StateFrame318(
            modifier = Modifier
                .weight(weight = 0.5f)
        )
        StateFrame319(
            modifier = Modifier
                .weight(weight = 0.5f)
        )
    }
}

@Composable
fun StateFrame318(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth().clip(shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 6.dp,
                vertical = 2.dp)
    ) {
        Text(
            text = "EN",
            color = Color(0xff181818),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun StateFrame319(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(brush = Brush.linearGradient(
                0f to Color(2, 125, 180, 228),
                1f to Color.Blue,
                start = Offset(0f, 9f),
                end = Offset(31f, 9f)
            ))
            .padding(horizontal = 6.dp),
    ){

    }}