package com.example.siaga.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.siaga.ui.component.OutlinedTextFieldComponent
import com.example.siaga.R
import com.example.siaga.model.Location
import com.example.siaga.ui.component.OutlinedButtonComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siaga.ui.component.MapsComponent
import com.example.siaga.viewmodel.LocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("Range", "MissingPermission")
@Composable
fun Location(navController: NavHostController) {
    var text by rememberSaveable { mutableStateOf("") }
    val locations = listOf(
        Location("Lokasi Saat Ini", "Jl Agus"),
        Location("Lokasi Saat Ini", "Jl Agus")
    )


    val viewModel: LocationViewModel = viewModel(modelClass = LocationViewModel::class.java)
    val context = LocalContext.current
    viewModel.requestLocation(context)

    val location = viewModel.locationUiState
    val latLangLocation = LatLng(location.latitude, location.longitude)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(latLangLocation,10f,1f,1f)
    }
    LaunchedEffect(latLangLocation) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLng(latLangLocation))
    }

    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Pilih Lokasi",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                OutlinedButtonComponent(
                    OnClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(0.dp)
                        .width(140.dp),
                    borderColor = Color.Red
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "",
                            tint = Color.Red
                        )
                        Text(
                            "Pilih Lewat Peta",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Add space between text and search field
            OutlinedTextFieldComponent(value = location.address, label = "Cari Alamat", type = "Search") {}
            Spacer(modifier = Modifier.height(16.dp)) // Add space between search field and map

            MapsComponent(locations= listOf(latLangLocation), cameraPositionState = cameraPositionState )


    }
}
    }

@Composable
fun LocationList(location: String, address: String) {
    Column(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Text(
            text = location,
            modifier = Modifier.padding(vertical = 4.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )
        Text(text = address)
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top = 8.dp))
    }
}

//@Preview
//@Composable
//fun PreviewLocation() {
//    Location(navController = rememberNavController())
//}
