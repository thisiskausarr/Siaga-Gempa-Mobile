package com.example.siaga.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.ButtonComponent
import com.example.siaga.ui.component.OutlinedButtonComponent
import com.example.siaga.ui.component.PopUpComponent
import com.example.siaga.viewmodel.LocationViewModel
import com.example.siaga.viewmodel.ReportViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun Sos(navController: NavHostController, modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val context = LocalContext.current
    val viewModel: ReportViewModel = viewModel(modelClass = ReportViewModel::class.java)
    var statusReported by remember{ mutableStateOf("panding")}
    viewModel.getReported(context)
    Log.d("report",viewModel.reportUiState.isReported.toString())

    val isReported  =viewModel.reportUiState.isReported
    val openDialog = remember { mutableStateOf(false) }
    val locationViewModel: LocationViewModel = viewModel(modelClass = LocationViewModel::class.java)
    locationViewModel.requestLocation(context = LocalContext.current)
    val dialogType = remember {
        mutableStateOf("Success")
    }
    if (openDialog.value){
        Popup(  alignment = Alignment.BottomCenter,
            properties = PopupProperties() ) {
            if(dialogType.value ==="Success"){
                PopUpComponent(
                    title = "Laporan Selesai",
                    description = "Terimakasih atas laporan anda. Tetap waspada terhadap gempa susulan  dan pastikan keamanan anda.",
                    onSuccess = {
                        openDialog.value = false
                        viewModel.ResetReported(statusReported,context)

                    },
                    onCanceled = {
                        openDialog.value = false

                    })
            }
            if (dialogType.value ==="Canceled"){
                PopUpComponent(title = "Batalkan Laporan", description = "",onSuccess = {
                    openDialog.value = false
                    viewModel.ResetReported(statusReported,context)

                },
                    onCanceled = {
                        openDialog.value = false

                    })

            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(

            painter = painterResource(id = if (viewModel.reportUiState.isReported) R.drawable.mobile else R.drawable.sosnew ),
            contentDescription = "Icon",
            modifier = Modifier
                .size(300.dp)
                .offset(x = 10.dp)
                .clickable {
                    viewModel.ChangeLocation(LatLng( locationViewModel.locationUiState.latitude,locationViewModel.locationUiState.longitude))
                    viewModel.CreateNewReport(context)
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (!viewModel.reportUiState.isReported) {
                "Tekan SOS jika anda\n" +
                        "dalam keadaan darurat\n" +
                        "dan butuh pertolongan cepat"
            } else {
                "Laporan Masuk \n" +
                        "Bantuan akan segera datang\n" +
                        "ke tempatmu"
            },
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 16.dp)
        )



        Spacer(modifier = Modifier.height(8.dp))
        if(!viewModel.reportUiState.isReported)LocationComponent(address=locationViewModel.locationUiState.address,navController)
        else ReportedButton(onReported = {
            dialogType.value="Success"

            openDialog.value = true
            statusReported = "success"
        }, onCancelReport = {
            dialogType.value="Canceled"

            openDialog.value = true
            statusReported = "canceled"

        })
       }}

@Composable
fun ReportedButton(onReported:()->Unit,onCancelReport:()->Unit){
    Column {

        ButtonComponent(label = "Bantuan Sudah Datang", OnClick = onReported)

        OutlinedButtonComponent(
            OnClick = onCancelReport,
            borderColor = Color.Black,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Batalkan Laporan",
                color = Color(0xFFFA0101), // Warna teks
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
//        ButtonComponent( label = "Daftar", OnClick = { navController.navigate(Screens.Final_Sosialization.screen)})
//        OutlinedButtonComponent(OnClick =  onClose ,borderColor=Color.Black){
//            Text(text =  "Batal",color = Color(0xFFFA0101),style = TextStyle(fontWeight = FontWeight.Bold))
//        }
    }

    }
@Composable
fun LocationComponent(address:String,navController:NavHostController){
    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(140.dp)
                .clip(RoundedCornerShape(50.dp))
                .clickable { navController.navigate(Screens.Location.screen) },
            shape = RoundedCornerShape(14.dp),

            ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color(236, 232, 232, 255))
                    .fillMaxHeight()
                    .fillMaxWidth(),


                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(elevation = 50.dp, CircleShape)

                        .background(Color(0x32039FE6), shape = CircleShape), // Set the background color to pink
                    painter = painterResource(id = R.drawable.loc),
                    contentDescription = "entypo:location-pin",
                    tint = Color(0xE6008DFD)
                )

                Spacer(modifier = Modifier.width(30.dp)) // Add some space between the icon and text

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Lokasimu saat ini",
                        color = Color(0xff1d1a1a),
                        lineHeight = 1.25.em,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Text(
                        text = address,
                        color = Color(0xff1d1a1a),
                        lineHeight = 1.25.em,
                        style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                }
            }
        }
    }
}



