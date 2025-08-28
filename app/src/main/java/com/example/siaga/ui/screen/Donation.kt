package com.example.siaga.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.siaga.R
import com.example.siaga.model.Donation
import com.example.siaga.navigation.Screens
import com.example.siaga.viewmodel.DonationViewModel

data class DonationtionItems(
    val location: String,
    val date: String,
    val time: String,
    val title: String,
    val imageResId: Int,
)

@Composable
fun Donation(navController: NavController) {
    val donationViewModel= viewModel(modelClass=DonationViewModel::class.java)
    donationViewModel.getAllDonations()
    val donations = donationViewModel.donations
    Log.d("donations",donations.toString())

        // Add more items if needed
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Edukasi Gempa",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            donations.forEach { item ->
                DonationItemView(item,navController)
            }
        }
    }
}

@Composable
fun DonationItemView(item: Donation,navController: NavController) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .height(160.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
//                    .clip(RoundedCornerShape(25.dp))
                    .clickable {
                        // Handle image click here
                    }
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navController.navigate("Payment/${item.id}")
                        }
                )
            }
            Box(modifier = Modifier.padding(start = 20.dp)) {
                Column {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}
//
@Preview(showBackground = true)
@Composable
fun PreviewEducation() {
    Donation(navController = rememberNavController())
}