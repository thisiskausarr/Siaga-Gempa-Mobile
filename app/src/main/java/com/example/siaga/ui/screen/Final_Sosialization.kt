package com.example.siaga.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.ButtonComponent

@Composable
fun Final_Sosialization(navController: NavHostController, modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.size),
            contentDescription = "Icon",
            modifier = Modifier
                .size(300.dp)
                .offset(x = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SELAMAT!",
            color = Color(0xffd8101a),
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Kamu telah terkonfirmasi akan berpartisipasi pada\n acara sosialisasi yang diselenggarakan oleh Badan\n Nasional Penanggulangan Bencana (BNPB)",
            color = Color(0xFF030303),
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.qr),
            contentDescription = "Icon",
            modifier = Modifier
                .size(150.dp)
                .offset(x = 10.dp)
        )
        ButtonComponent( label = "Selesai", OnClick = {  navController.navigate(Screens.Home.screen)})


    }}
//
//@Composable
//fun ComponentFinal(
//    modifier: Modifier = Modifier,
//    imageId: Int,
//    title: String,
//    description: String
//) {
//    Surface(
//        modifier = modifier
//            .fillMaxWidth()
//            .requiredHeight(100.dp)
//            .clip(RoundedCornerShape(14.dp)),
//        shape = RoundedCornerShape(14.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .background(Color(0xFFE2DCDC), shape = RoundedCornerShape(15.dp))
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(10.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(50.dp)
//                        .background(Color(0x33D8101A), shape = CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = imageId),
//                        contentDescription = "Icon",
//                        modifier = Modifier
//                            .size(50.dp)
//                            .shadow(elevation = 10.dp)
//                    )
//                }
//
//                Column(
//                    modifier = Modifier.padding(start = 8.dp)
//                ) {
//                    Text(
//                        text = title,
//                        color = Color(0xff1d1a1a),
//                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                    )
//                    Text(
//                        text = description,
//                        color = Color(0xff969696),
//                        lineHeight = 1.5.em,
//                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
//                        modifier = Modifier.padding(top = 8.dp)
//                    )
//                }
//            }
//        }
//    }
//}
