package com.example.siaga.ui.screen

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.MapsComponent
import com.example.siaga.ui.component.OutlinedTextFieldComponent
import com.example.siaga.viewmodel.DonationViewModel
import com.example.siaga.viewmodel.HomeViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState


@Composable
fun Payment(navController: NavController ,modifier: Modifier = Modifier,donationId:String?) {
    val donationViewModel = viewModel(modelClass = DonationViewModel::class.java)
    donationViewModel.getDonationById(donationId!!)

    EarthquakeInfo (
        navController = navController,
        data=donationViewModel.donation,
        viewModel = donationViewModel,
        donationId=donationId
        )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EarthquakeInfo( modifier: Modifier = Modifier,navController: NavController ,donationId:String,data:Donation,viewModel:DonationViewModel) {


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(14.dp),
        color = Color(0xfffbfafa),
        shadowElevation = 10.dp
    ) {
        Column(

            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column {


                AsyncImage(
                    model=data.image,
                    contentDescription ="",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)

                )

                Text(
                    text = data.title,
//                    color = Color(2, 125, 180, 228),
                    style = TextStyle(fontSize = 24.sp)
                )
                    Text(
                        text = "Target Rp. ${data.target}",
                    color = Color(2, 125, 180, 228),
                        style = TextStyle(fontSize = 24.sp)
                    )
                    Text(
                        text = "Terkumpul  Rp ${data.nominal}",
                        color = Color(127, 140, 146, 255),
                        style = TextStyle(fontSize = 20.sp)
                    )
                    Spacer(modifier = modifier.height(18.dp))
                    Row (modifier=Modifier.align(Alignment.CenterHorizontally)){

                        Image(
                            painter = painterResource(id = R.drawable.qr),
                            contentDescription = "",
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        )
                        OutlinedTextFieldComponent(value = viewModel.donationUiState.nominal, OnChange = {
                            viewModel.onNominalChange(it)
                        }, label = "Nominal Donasi",keyboardOptions=KeyboardOptions(keyboardType = KeyboardType.Number))
                    }

                    Spacer(modifier = Modifier.height(26.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 150.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(7.dp))
                                .background(color = Color(0xff969696)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bagikan",
                                textAlign = TextAlign.Center,
                                color = Color.White // Opsional: Mengatur warna teks
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clickable {
                                    viewModel.addDonation(donationId)
                                    navController.navigate(Screens.ReportDonation.screen)
                                }
                                .requiredWidth(width = 150.dp)
                                .requiredHeight(height = 36.dp)
                                .clip(shape = RoundedCornerShape(7.dp))
                                .background(color = Color(0xff508cae)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Selesaikan Donasi",
                                textAlign = TextAlign.Center,
                                color = Color.White // Opsional: Mengatur warna teks

                            )
                        }
                    }

                }

                }
        }
        }
    }


//
@Preview
@Composable
fun PreviewProperty1Default() {
//    Payment(navController = rememberNavController())
}