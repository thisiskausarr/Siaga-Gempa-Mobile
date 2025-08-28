package com.example.siaga.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.AlarmForegroundService
import com.example.siaga.R
import com.example.siaga.viewmodel.AlarmViewModel
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Alarm(navController: NavHostController) {
    val pushNotification= remember { mutableStateOf(false)}
//    val alarmNotification = remember { mutableStateOf(false)}
    val alarmViewModel = viewModel(modelClass = AlarmViewModel::class.java)
    val context = LocalContext.current

    try {
        val fin: FileInputStream = context.openFileInput("ALARM.txt")
        var a: Int
        val temp = StringBuilder()
        while (fin.read().also { a = it } != -1) {
            temp.append(a.toChar())
        }
        if(!alarmViewModel.alarmUiState.alarmNotification){

        Log.d("STATUS NOTIF",temp.toString())
        alarmViewModel.setAlarmNotification(temp.toString() =="true")
        }

        fin.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    if(alarmViewModel.alarmUiState.alarmNotification){
        val intent = Intent(context, AlarmForegroundService::class.java)
        context.startForegroundService(intent)
    }

    Box(modifier=Modifier.padding(horizontal = 15.dp, vertical = 10.dp)){
        Column {

            AlarmCard(
                title = "Notifikasi Gempa",
                description = "Anda akan mendapatkan Alarm hanya dalam bentuk notifikasi secara otomatis",
                type = "PUSH_NOTIFICATION",
                value=pushNotification.value,
                onCHecked = {status->pushNotification.value=status})
            AlarmCard(
                title = "Alarm Penuh",
                description = "Anda akan mendapatkan\n Alarm getar dan suara\n dalam bentuk penuh\n pada layar smartphone anda \nsecara otomatis",
                type = "FORCE_NOTIFICATION",
                value=alarmViewModel.alarmUiState.alarmNotification,
                onCHecked =  {status->
                    alarmViewModel.setAlarmNotification(status)
                    try {
                        val fos: FileOutputStream =
                            context.openFileOutput("ALARM.txt", Context.MODE_PRIVATE)
                        fos.write(status.toString().toByteArray())
                        fos.flush()
                        fos.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    if (status) {
                        val intent = Intent(context, AlarmForegroundService::class.java)
                        context.startForegroundService(intent)
                    } else {
                        val intent = Intent(context, AlarmForegroundService::class.java)
                        context.stopService(intent)

                }

                })
        }
    }
}
@Composable
fun AlarmCard(title:String,description:String,type:String,value:Boolean,onCHecked:(status:Boolean)->Unit){
    Card (
        Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(10.dp))
            .padding(10.dp), colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
    ) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)
        ){
       Text(
           text =title,
           style = TextStyle(
               fontSize = 24.sp,
               fontWeight = FontWeight.ExtraBold
           )
       )
        Switch(
            checked = value,
            onCheckedChange = onCHecked,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(160, 2, 2, 255),
            ),
            modifier=Modifier.shadow(elevation = -(5).dp, shape = CircleShape)
        )
    }
        Box(modifier = Modifier.padding(15.dp)){

            if(type=="PUSH_NOTIFICATION"){
                Text(text = description,style= TextStyle(
                    fontSize=18.sp,
                    fontWeight = FontWeight.SemiBold
                ), color = Color.Gray)
            Row(
                Modifier
                    .padding(top = 60.dp)
                    .shadow(10.dp, RoundedCornerShape(8.dp))
                    .background(
                        Color(0xFFFFFFFF),
                        RoundedCornerShape(8.dp)
                    ),
                horizontalArrangement = Arrangement.Center
            ){

                Image(painter = painterResource(id = R.drawable.logo), contentDescription ="Logo",Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column (Modifier.padding(vertical=20.dp)){
                    Text(text="Siaga",style= TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    )
                    Text(text="Gempa Dirasakan Mag:5.2 (Pusat Gempa berjarak 100 KM dari lokasi anda) klik untuk panduan mitigasi")
                }
            }
            }
            else{
                Row (Modifier){
                    Text(text = description,style= TextStyle(
                        fontSize=18.sp,
                        fontWeight = FontWeight.SemiBold
                    ), color = Color.Gray)
                    Image(painter = painterResource(id = R.drawable.alarm_penuh), contentDescription = "asd",Modifier.size(200.dp))
                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewCard(){
//   Alarm(navController = rememberNavController()

}

//@Preview
//@Composable
//fun PreviewSwitch(){
//    Box(Modifier.fillMaxWidth().height(100.dp)){
//
//    Switch2()
//    }
//
//}




@Composable
fun Switch2(
    scale: Float = 2f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true) // Initially the switch is ON
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            }
    ) {
        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }

}