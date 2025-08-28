package com.example.siaga.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import com.example.siaga.model.History
import com.example.siaga.navigation.Screens
import com.example.siaga.viewmodel.HistoryViewModel

data class HistoryItem(
    val location:String,
    val date :String,
    val time:String,
    val magnitudo: String
)

@Composable
fun History(navController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel(modelClass = HistoryViewModel::class.java)
    viewModel.LoadHistory()
    val historyItems = viewModel.history.data

//    val historyItems = remember {
//        listOf(
//            HistoryItem("Kuningan,Indonesia", "1 Februrary 2024", "11.30 PM","7.3"),
//            HistoryItem("Kuningan,Indonesia", "1 Februrary 2024", "11.30 PM","7.3"),
//            HistoryItem("Kuningan,Indonesia", "1 Februrary 2024", "11.30 PM","7.3"),
//        )
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "History",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState(),)

        ) {
            historyItems.forEach { item ->
                HistoryItemView(item,navController)
            }

        }

    }
}

@Composable
fun HistoryItemView(item: History,navController:NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()  // Set to fill max width
            .padding(vertical = 8.dp)
            .height(160.dp), // Increased height for better layout

        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick =  {navController.navigate(Screens.Hazard_Maps.screen)}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                Modifier.shadow(elevation = 20.dp, CircleShape)
                    .background(Color(250, 250, 250, 255), CircleShape)
//                    .border(
//                        width = 5.dp, Color.White,
//                        CircleShape
//                    )

            ) {
                Box(modifier = Modifier.padding(vertical = 22.dp, horizontal = 20.dp)) {

                    Text(
                        text = item.mag,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color=Color.Blue
                            ),
                    )

                }
            }
            Box(Modifier.padding(start=20.dp)){
                Column {
                    Text(
                        text=item.place,
                        style= TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color=Color.Black
                        )
                    )
                    Row {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription ="Date")
                        Text(item.date)
                    }

                    Row {
                        Icon(imageVector = Icons.Filled.Place, contentDescription ="Date")
                        Text(item.time)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHistory(){
    History(navController = rememberNavController())
}