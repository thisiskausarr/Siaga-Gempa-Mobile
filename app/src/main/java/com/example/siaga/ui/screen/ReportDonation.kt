package com.example.siaga.ui.screen

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.siaga.R
import com.example.siaga.model.Donation
import com.example.siaga.viewmodel.DonationViewModel


@Composable
fun ReportDonation(navController: NavController ,modifier: Modifier = Modifier) {
    val donationViewModel: DonationViewModel = viewModel(modelClass=DonationViewModel::class.java)
    donationViewModel.getAllDonations()
    DonationDetail(data = donationViewModel.donations)
}

@Composable
fun DonationDetail( modifier: Modifier = Modifier, data:List<Donation>) {


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(14.dp),
        color = Color(0xfffbfafa),
        shadowElevation = 10.dp
    ) {
        LazyColumn(

            modifier = Modifier.padding(16.dp)
        ) {
            items(data){d->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column {


                    AsyncImage(
                        model=d.image,
                        contentDescription ="",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)

                    )

                    Text(
                        text = d.title,
//                    color = Color(2, 125, 180, 228),
                        style = TextStyle(fontSize = 24.sp)
                    )
                    Text(
                        text = "Terkumpul  Rp ${d.nominal}",
                        color = Color(1, 170, 248, 255),
                        style = TextStyle(fontSize = 20.sp)
                    )
//
                    }

                }

            }
        }

        }
    }



//
@Preview
@Composable
fun PreviewReportDonation() {
    ReportDonation(navController = rememberNavController())
}