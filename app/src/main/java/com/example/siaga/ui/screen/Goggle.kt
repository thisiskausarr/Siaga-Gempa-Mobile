package com.example.siaga.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import com.example.siaga.navigation.Screens

@Composable
fun Goggle(navController: NavHostController) {
    Box(modifier = Modifier.background(color = Color(0, 0, 0, 150))) {
        Box(
            Modifier
                .requiredHeight(height = 400.dp)
                .requiredWidth(width = 428.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(color = Color(192, 15, 15))
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
                    text = "Pilih Akun Google",
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Example Google accounts (these would be dynamically loaded in a real app)
                val googleAccounts = listOf("user1@gmail.com", "user2@gmail.com", "user3@gmail.com")

                googleAccounts.forEach { account ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                // Handle Google account selection
                                navController.navigate(Screens.Home.screen)
                            }
                            .border(1.dp, Color(192, 15, 15), RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.goggle), // Google logo
                                contentDescription = "Google Logo",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = account, color = Color(192, 15, 15))
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewGoogle(){
    Goggle(navController = rememberNavController())
}