package com.example.siaga.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import com.example.siaga.ui.component.ButtonComponent
import com.example.siaga.ui.component.MapsComponent

@Composable
fun HazardMaps(navController: NavHostController,modifier: Modifier = Modifier) {
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
                        .height(300.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(16.dp))
                ) {
//                    MapsComponent()
//                    OsmdroidMapView()
                    //                Image(
//                    painter = painterResource(id = R.drawable.maps),
//                    contentDescription = "Map",
//                    contentScale = ContentScale.FillBounds,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//                        .pointerInput(Unit) {
//                            detectTransformGestures { _, pan, _, _ ->
//                                val newX = offsetX + pan.x
//                                val newY = offsetY + pan.y
//                                if (newX in minOffsetX..maxOffsetX) {
//                                    offsetX = newX
//                                }
//                                if (newY in minOffsetY..maxOffsetY) {
//                                    offsetY = newY
//                                }
//                            }
//                        }
//
//                )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    WhiteBackgroundIcon(
                        imageId = R.drawable.time,
                        contentDescription = "Time Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 23.dp
                    )
                    WhiteBackgroundIcon(
                        imageId = R.drawable.magnuitudo,
                        contentDescription = "Magnitude Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 23.dp
                    )
                    WhiteBackgroundIcon(
                        imageId = R.drawable.km,
                        contentDescription = "KM Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 23.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Jarak dari Anda:",
                    color = Color(0xffd8101a),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "150 km",
                    color = Color(0xFF090808),
                    style = TextStyle(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Potensi Tsunami:",
                    color = Color(0xFFF70707),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "Tidak",
                    color = Color(0xFF131212),
                    style = TextStyle(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Wilayah Dirasakan:",
                    color = Color(0xFFF70606),
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "Jakarta I, Jakarta II, Jakarta III, Jakarta IV, Jakarta V",
                    color = Color(0xFF141212),
                    style = TextStyle(fontSize = 14.sp)
                )
                ButtonComponent(label = "bagikan", OnClick = {}) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Bagikan")
                    Text(text = "Bagikan")
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewHazardMaps() {
    HazardMaps(navController = rememberNavController())
}