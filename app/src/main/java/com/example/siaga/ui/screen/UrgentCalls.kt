package com.example.siaga.ui.screen



import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.navigation.Screens
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

@Composable
fun UrgentCall(navController: NavHostController) {
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

                BoxCall(
                    navController = navController,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            }
        }
    }
}

@Composable
fun BoxCall(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Call(
            imageId = R.drawable.bnpb,
            title = "Badan Nasional\n" +
                    "Penanggulangan Bencana\n" +
                    "117",
            description = "",
            phoneNumber = "117", // Replace with the actual phone number
            onClick = { navController.navigate(Screens.Chatbot.screen) }
        )
        Spacer(modifier = Modifier.height(13.dp))
        Call(
            imageId = R.drawable.bsn,
            title = "Badan SAR Nasional\n" +
                    "115",description = "",
            phoneNumber = "115", // Replace with the actual phone number
            onClick = { navController.navigate(Screens.Chatbot.screen) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Call(
            imageId = R.drawable.ambl,
            title = "Ambulans Darurat\n" +
                    "118",
            description = "",
            phoneNumber = "118", // Replace with the actual phone number
            onClick = { navController.navigate(Screens.Chatbot.screen) }
        )
    }
}

@Composable
fun Call(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String,
    phoneNumber: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permission granted, proceed with the call
                val intent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                context.startActivity(intent)
            } else {
                // Permission denied, handle the case
                // You might show a message to the user
            }
        }
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(110.dp)
            .clickable {
                launcher.launch(android.Manifest.permission.CALL_PHONE)
            },
        shape = RoundedCornerShape(14.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color(224, 224, 224, 0), shape = RoundedCornerShape(10.dp))
        ) {
            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = imageId), contentDescription ="image" )

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

@Preview
@Composable
fun ItemsPreview(){
    val navController = rememberNavController()
    Call(
        imageId = R.drawable.ambl,
        title = "Ambulans Darurat\n" +
                "118",
        description = "",
        phoneNumber = "118", // Replace with the actual phone number
        onClick = { navController.navigate(Screens.Chatbot.screen) }
    )
}