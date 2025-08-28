package com.example.siaga

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import android.os.Build
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.glance.action.clickable
import androidx.glance.color.colorProviders
import com.example.siaga.navigation.Screens


class AppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            GlanceTheme {
//                Box()
//                ) {
                    App(context)

//                }
//                Widget()
            }
        }
    }
}

@Composable
fun App(context:Context) {
    MainActivity.ChangeRoute(Screens.Sos)
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        Intent(context,MainActivity::class.java).apply {

            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        },
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,


    )
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
//            .background(Color(0xfffbfafa))
                .background(imageProvider = ImageProvider(R.drawable.backgroud_shaped)),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Baris Logo
        Image(modifier = GlanceModifier.size(150.dp),
            provider = ImageProvider(R.drawable.logo),
            contentDescription = "a",)
        Image(modifier = GlanceModifier.fillMaxWidth().height(4.dp), provider = ImageProvider(R.drawable.line), contentDescription = "a",)

        Row(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

        ) {
            Column(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
//                Spacer(modifier = GlanceModifier.height(16.dp))
                Row(
                    modifier = GlanceModifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WhiteBackgroundIcon(
                        imageId = R.drawable.magnuitudo,
                        contentDescription = "Magnitude Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 35.dp
                    )
                    Spacer(modifier = GlanceModifier.width(16.dp))
                    WhiteBackgroundIcon(
                        imageId = R.drawable.clock,
                        contentDescription = "Time Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 35.dp
                    )
                    Spacer(modifier = GlanceModifier.width(16.dp))
                    WhiteBackgroundIcon(
                        imageId = R.drawable.mg,
                        contentDescription = "KM Icon",
                        tint = Color(0xFFC70000),
                        iconSize = 35.dp
                    )
                }
                Spacer(modifier = GlanceModifier.height(16.dp))
                Row () {
                    Column {

                Text(
                    text = "Jarak dari Anda:",

                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "150 km",
//            color = Color(0xFF090808),
                    style = TextStyle(fontSize = 15.sp,)
                )
                    }
                    Spacer(modifier = GlanceModifier.width(30.dp))

                Column {


                Text(
                    text = "Potensi Tsunami",
//            color = Color(0xFFF70707),
                    style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Tidak",
//            color = Color(0xFF131212),
                    style = TextStyle(fontSize = 15.sp)
                )}
                }


                Spacer(modifier = GlanceModifier.height(8.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = GlanceModifier.fillMaxWidth()) {
                Text(
                    text = "Wilayah Dirasakan:",
//            color = Color(0xFFF70606),
                    style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Jakarta I, Jakarta II, Jakarta III, Jakarta IV, Jakarta V",
//            color = Color(0xFF141212),
                    style = TextStyle(fontSize = 15.sp)
                )
            }}

        }
        Spacer(modifier = GlanceModifier.height(10.dp))
        Image(modifier = GlanceModifier.size(90.dp).clickable {
            pendingIntent.send()
        }, provider = ImageProvider(R.drawable.so), contentDescription = "a",)

    }}
@SuppressLint("ResourceType")
@Composable
fun WhiteBackgroundIcon(

    imageId: Int,
    contentDescription: String,
    tint: Color,
    iconSize: Dp
) {
//    Button(text = "asdasd" , onClick ={} )

    Box(
        modifier = GlanceModifier
            .width(82.dp)
            .background(imageProvider = ImageProvider(R.drawable.icon_shaped))
            .padding(8.dp)


    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Image(
                provider = ImageProvider(imageId),
                contentDescription = contentDescription,
                modifier = GlanceModifier.size(iconSize)
            )
            Spacer(modifier = GlanceModifier.height(8.dp))
            Text(text = "12.00 WIB")
        }
    }
//    Icon(
//        modifier = Modifier
//            .size(100.dp), // Adjust the icon size as needed
//        painter = painterResource(id = R.drawable.soss),
//        contentDescription = "", // Content description for accessibility
//        tint = Color.Unspecified, // Use Color.Unspecified to retain the original icon color
//
//    )
}
