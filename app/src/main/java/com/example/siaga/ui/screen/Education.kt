package com.example.siaga.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import android.content.Intent
import android.net.Uri

data class EducationItem(
    val title: String,
    val description: String,
    val imageResId: Int,
    val youtubeLink: String // Add youtube link
)

@Composable
fun Education(navController: NavController) {
    val educationItems = remember {
        listOf(
            EducationItem("Apa itu Gempa", "Bagaimana Gempa Bumi Terjadi", R.drawable.apaitugempa, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids"),
            EducationItem("Penyebab Gempa", "Memahami Penyebab Gempa Bumi", R.drawable.penyebabgempa, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids"),
            EducationItem("Gempa Bumi Terjadi", "Hal yang Harus Dilakukan Saat Terjadi Gempa", R.drawable.gempabumiterjadi, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids"),
            EducationItem("Apa itu Gempa", "Bagaimana Gempa Bumi Terjadi", R.drawable.apaitugempa, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids"),
            EducationItem("Penyebab Gempa", "Memahami Penyebab Gempa Bumi", R.drawable.penyebabgempa, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids"),
            EducationItem("Gempa Bumi Terjadi", "Hal yang Harus Dilakukan Saat Terjadi Gempa", R.drawable.gempabumiterjadi, "https://www.youtube.com/watch?v=O6GjGUWmRmc&ab_channel=StudycleKids")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Edukasi Gempa",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            educationItems.forEach { item ->
                EducationItemView(item)
            }
        }
    }
}

@Composable
fun EducationItemView(item: EducationItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(160.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(item.youtubeLink)
                        }
                        context.startActivity(intent)
                    }
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(modifier = Modifier.padding(start = 20.dp)) {
                Column {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = item.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewEducation() {
//    Education(navController = rememberNavController())
//}