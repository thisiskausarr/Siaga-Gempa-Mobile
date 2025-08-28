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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.ButtonComponent
import com.example.siaga.ui.component.MapsComponent
import com.example.siaga.viewmodel.HistoryViewModel
import com.example.siaga.viewmodel.HomeViewModel

@Composable
fun History_Detail(navController: NavHostController,modifier: Modifier = Modifier) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val viewModel: HistoryViewModel = viewModel(modelClass = HistoryViewModel::class.java)
    viewModel.LoadLocation()
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
                    .height(600.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                MapsComponent(
                    locations = viewModel.locationHistory.location
                )
//
            }
        }
    }
}