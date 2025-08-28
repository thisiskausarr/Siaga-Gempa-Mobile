package com.example.siaga.ui.screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.proto.LayoutProto.HorizontalAlignment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.siaga.R
import com.example.siaga.navigation.Screens

data class SosialisasiItem(
    val location:String,
    val date :String,
    val time:String,

)

@Composable
fun Sosialization(navController: NavController, modifier: Modifier = Modifier) {
    val items = listOf(
        SosialisasiItem(location = "ZOOM Meeting", date = "2024-05-21", time = "10:00 AM"),
        SosialisasiItem(location = "ZOOM Meeting", date = "2024-05-22", time = "11:00 AM")
        // Add more items if needed
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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

                items.forEach { item ->
                    BoxSosial(
                        item = item,
                        navController = navController,
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable

fun BoxSosial(item: SosialisasiItem, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ComponentBox(
            imageId = R.drawable.bnpb,
            title = "Sosialisasi Mitigasi Gempa Bumi",
            description = "Aparatur Sipil Negara",
            location = item.location,
            date = item.date,
            time = item.time,
            onClick = { navController.navigate(Screens.Register_Sosialization.screen) }
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun ComponentBox(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String,
    location: String,
    date: String,
    time: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(100.dp)  // Adjusted height to accommodate additional information
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color(226, 220, 220, 255),shape= RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0x33D8101A), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = imageId),
                            contentDescription = "Icon",
//                            colorFilter = ColorFilter.tint(Color(0xFFF5F3F3)),
                            modifier = Modifier
                                .size(50.dp)
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
                        Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth().padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically){
                            // Add location, date, and time below the description
                            Text(
                                text = location,
                                color = Color.Black,
                                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),

                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Date",
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = date,
                                    color = Color.Black,
                                    style = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Place,
                                    contentDescription = "Time",
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = time,
                                    color = Color.Black,
                                    style = TextStyle(fontSize = 14.sp),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
