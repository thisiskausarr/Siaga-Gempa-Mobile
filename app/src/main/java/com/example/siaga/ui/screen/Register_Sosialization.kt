package com.example.siaga.ui.screen

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.CardContainerColors
import com.example.siaga.R
import com.example.siaga.ui.component.ButtonComponent
import com.example.siaga.ui.component.OutlinedButtonComponent
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.siaga.navigation.Screens


@Composable
fun Register_Sosialization(navController: NavHostController,modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val openDialog = remember { mutableStateOf(false) }

    if(openDialog.value){
    Popup(  alignment = Alignment.BottomCenter,
        properties = PopupProperties() ) {
        popup(onClose = {openDialog.value=false},navController=navController)

    }


    }
    Column (modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 5.dp)
        .fillMaxHeight()){


        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "BNPB Mengadakan Sosialisasi\nMitigasi Gempa Bumi",
            color = Color(0xffd8101a),
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)

        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Deskripsi Kegiatan :\n" +
                    "BNPB akan melakukan sosialisasi dan bimbingan teknis (bimtek) sistem manajemen pengetahuan bencana yang berlangsung di Jakarta pada 21 Maret 2024. Wilayah penyelenggaraan merupakan salah satu prioritas dari program Indonesia Disaster Resilience Initiatives Project (IDRIP).\n" +
                    "\n" +
                    "1.Persyaratan Peserta :\n" +
                    "2.Pendaftaran Online\n" +
                    "3.Identitas Diri KTP/SIM\n" +
                    "4.Konfirmasi Kehadiran\n" +
                    "5.Maksimal Usia 65 Tahun\n",
//            color = Color(0xFFF70707),
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Pembuat Acara",
            color = Color(0xFFF70606),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        ComponentBox(
            imageId = R.drawable.bnpb,
            title="Badan Nasional\n" +
                    "Penanggulangan Bencana",
            description = ""
        )
        ButtonComponent(label = "daftar", OnClick = {
            openDialog.value = true
        }) {

            Text(text = "Daftar Sekarang")
        }


    }
}



@Composable
fun ComponentBox(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(100.dp)  // Adjusted height to accommodate additional information
            .clip(RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp)
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .background(Color(226, 220, 220, 255),shape= RoundedCornerShape(15.dp))
//        ) {
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
                    }
                }
            }
        }
    }


@Composable
fun popup(
    onClose:()->Unit,
    navController:NavController
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()

            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(20.dp)).shadow(elevation = -10.dp, shape = RoundedCornerShape(20.dp)),
//        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally){

        Image(
            painter = painterResource(R.drawable.yes),
            contentDescription = "Icon",
//                            colorFilter = ColorFilter.tint(Color(0xFFF5F3F3)),
            modifier = Modifier
                .size(200.dp)
//                .shadow(elevation = 10.dp)
        )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Apakah kamu yakin ingin berpartisipasi?",
                color = Color(0xFFF70606),
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Kamu akan diarahkan ke halaman detail kegiatan",
                color = Color(0xFF8A8A8A),
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
            )





            ButtonComponent( label = "Daftar", OnClick = {  navController.navigate(Screens.Final_Sosialization.screen)})
            OutlinedButtonComponent(OnClick =  onClose ,borderColor=Color.Black){
                Text(text =  "Batal",color = Color(0xFFFA0101),style = TextStyle(fontWeight = FontWeight.Bold))
            }
//            ButtonComponent( label = "Batal", OnClick = {},     )

//            Text(text = "Daftar Sekarang")


        }

    }
}
