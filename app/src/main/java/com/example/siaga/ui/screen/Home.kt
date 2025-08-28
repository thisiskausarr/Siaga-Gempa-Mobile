package com.example.siaga.ui.screen

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.siaga.R
import kotlin.math.roundToInt
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.siaga.model.Location
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.MapsComponent
import com.example.siaga.viewmodel.HomeViewModel
import com.example.siaga.viewmodel.LocationViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


val imageSize = Size(300f, 200f) // Ganti dengan ukuran sebenarnya dari gambar Anda

val minOffsetX = 0f
val maxOffsetX = imageSize.width
val minOffsetY = 0f
val maxOffsetY = imageSize.height
@Composable
fun Home(navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val locationViewModel:LocationViewModel = viewModel(modelClass = LocationViewModel::class.java)
    locationViewModel.requestLocation(context = LocalContext.current)

    viewModel.launchEarthQuake()
    val location = listOf(viewModel.mapstate.location)

    Box(

        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Surface( modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clickable { navController.navigate(Screens.Location.screen) }) {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,


            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),

                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "entypo:location-pin",
                    tint = Color(2, 125, 180, 228)
                )
                Text(
                    text = locationViewModel.locationUiState.address,
                    color = Color(0xff1d1a1a),
                    lineHeight = 1.25.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 278.dp)
                )
                Icon(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.panah),
                    contentDescription = "Fill 1",

                    )
            }
        }
            IconsRight(
                navController = navController,
                viewModel=viewModel,
                locations=location
            )

    }
}

@Composable
fun IconsRight(viewModel: HomeViewModel, locations: List<LatLng>, navController:NavController, modifier: Modifier = Modifier) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(viewModel.mapstate.location,10f,1f,1f)
    }

    var columnScrollingEnabled by remember { mutableStateOf(true)}
    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            columnScrollingEnabled = true
        }
    }
            Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), columnScrollingEnabled)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                BoxSelect(
                    navController = navController,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    onClick = {navController.navigate(Screens.Home.screen)}


                )
                EarthquakeInfo(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(500.dp)
                ,cameraPositionState=cameraPositionState,
                    onMapTouched = {
                        columnScrollingEnabled = false

                    },
                    viewModel = viewModel,
                    locations = locations
                )
            }
        }
    }
}


@Composable
fun BoxSelect(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Component(
            imageId = R.drawable.chatbot,
            title = "Chatbot",
            tint = Color(2, 125, 180, 228),
            description = "Tanyakan apa saja seputar informasi gempa bumi di Indonesia.",
                    //onClick = { navController.navigate(Screens.Chatbot.screen) }
            onClick = { navController.navigate(Screens.Chatbot.screen) }
            )

        Spacer(modifier = Modifier.height(16.dp))
        Component(
            imageId = R.drawable.call,
            title = "Telepon Darurat",
            tint = Color(2, 125, 180, 228),
            description = "Selain fitur SOS, kamu bisa hubungi kami jika dalam keadaan darurat untuk mempercepat penanggulangan.",
            onClick = { navController.navigate(Screens.UrgentCall.screen) }


        )
        Spacer(modifier = Modifier.height(16.dp))
        Component(
            imageId = R.drawable.alrm,
            tint = Color(2, 125, 180, 228),
            title = "Alarm Gempa Bumi",
            description = "Kamu bisa memilih notifikasi yang akan diterima saat gempa.",
            onClick = { navController.navigate(Screens.Alarm.screen) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Gempa Terkini",
            color = Color(2, 125, 180, 228),

            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        Text(
            text = "Update Gempa Terkini Yang Sedang Terjadi",
            color = Color(0xff1d1a1a),
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )


    }
}

@Composable
fun Component(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String,
    onClick: () -> Unit,
    tint: Color
//    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()

            .requiredHeight(110.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp)
//
    ) {
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color(255, 248, 248, 235))


        ) {
            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(255, 255, 255, 255), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Icon",
                        colorFilter = ColorFilter.tint(Color(2, 125, 180, 228)),
                        modifier = Modifier
                            .size(30.dp)
                            .shadow(elevation = 10.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = title,
                        color = Color(0xff1d1a1a),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = description,
                        color = Color(0xff969696),
                        lineHeight = 1.5.em,
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EarthquakeInfo(viewModel: HomeViewModel,locations:List<LatLng>,modifier: Modifier = Modifier,cameraPositionState:CameraPositionState,onMapTouched:()->Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

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
                    .height(200.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                MapsComponent(cameraPositionState, locations = locations,modifier=Modifier.pointerInteropFilter(onTouchEvent ={
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            onMapTouched()
                            false
                        }
                        else -> {

                            true
                        }
                    }

                }))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WhiteBackgroundIcon(
                    imageId = R.drawable.time,
                    contentDescription = "Time Icon",
                    label = viewModel.mapstate.time.toString(),
                    tint = Color(2, 125, 180, 228),
                    iconSize = 23.dp
                )
                WhiteBackgroundIcon(
                    imageId = R.drawable.magnuitudo,
                    contentDescription = "Magnitude Icon",
                    label = viewModel.mapstate.mag.toString(),
                    tint = Color(2, 125, 180, 228),
                    iconSize = 23.dp
                )
                WhiteBackgroundIcon(
                    imageId = R.drawable.km,
                    contentDescription = "KM Icon",
                    label = viewModel.mapstate.depth.toString() +" KM",

                    tint = Color(2, 125, 180, 228),
                    iconSize = 23.dp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Jarak dari Lokasi:",
                color = Color(2, 125, 180, 228),
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = viewModel.mapstate.distance.toString()    +" Km",
                color = Color(0xFF090808),
                style = TextStyle(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Potensi Tsunami:",
                color = Color(2, 125, 180, 228),

                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = if( viewModel.mapstate.tsunami ===true) "Ya" else "Tidak Ada",
                color = Color(0xFF131212),
                style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Wilayah Dirasakan:",
                color = Color(2, 125, 180, 228),
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = viewModel.mapstate.place.toString(),
                color = Color(0xFF141212),
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun WhiteBackgroundIcon(
    imageId: Int,
    contentDescription: String,
    label:String="",
    tint: Color,
    iconSize: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .shadow(8.dp, CircleShape)
            .background(Color.White, shape = CircleShape)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize),
            colorFilter = ColorFilter.tint(tint)

        )
        Spacer(modifier = Modifier.height(4.dp)) // Space between icon and label
        Text(
            text = label,
            color = Color.Black,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}



