package com.example.siaga.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun TabsDonation(navController: NavHostController) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Donasi", "Laporan Donasi")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = Color.Red,
                    height = 2.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (tabIndex == index) Color.Blue else Color.Black
                        )
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> Donation(navController = navController)
            1 -> ReportDonation(navController = navController)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTabsHistory() {
//    TabsHistory(navController = rememberNavController())
//}
