package com.example.siaga.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.compose
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.siaga.R
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.screen.Alarm
import com.example.siaga.ui.screen.Chatbot
import com.example.siaga.ui.screen.Education
import com.example.siaga.ui.screen.Final_Sosialization
import com.example.siaga.ui.screen.Forget_Password
import com.example.siaga.ui.screen.Goggle
import com.example.siaga.ui.screen.HazardMaps
import com.example.siaga.ui.screen.History
import com.example.siaga.ui.screen.History_Detail
import com.example.siaga.ui.screen.Home
import com.example.siaga.ui.screen.Location
import com.example.siaga.ui.screen.Login
import com.example.siaga.ui.screen.Notif
import com.example.siaga.ui.screen.Payment
import com.example.siaga.ui.screen.Profil
import com.example.siaga.ui.screen.Register
import com.example.siaga.ui.screen.Register_Sosialization
import com.example.siaga.ui.screen.ReportDonation
import com.example.siaga.ui.screen.Sos
import com.example.siaga.ui.screen.Sosialization
import com.example.siaga.ui.screen.TabsDonation
import com.example.siaga.ui.screen.TabsEducation
import com.example.siaga.ui.screen.TabsHistory
import com.example.siaga.ui.screen.UrgentCall
import com.example.siaga.viewmodel.AuthViewModel
import com.example.siaga.viewmodel.ProfileViewModel


@Composable
fun Navigation(defaultDestination:Screens) {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val isLogin = currentBackStackEntry?.destination?.route == Screens.Login.screen
    val isHome = currentBackStackEntry?.destination?.route == Screens.Home.screen
    val isHistory = currentBackStackEntry?.destination?.route == Screens.History.screen
    val isSos = currentBackStackEntry?.destination?.route == Screens.Sos.screen
    val isNotification = currentBackStackEntry?.destination?.route == Screens.Notif.screen
    Log.d("Nav",isNotification.toString())
    val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
    authViewModel.getCurremtUser()
    val isEducation =  currentBackStackEntry?.destination?.route == Screens.Education.screen
    val isProfile =  currentBackStackEntry?.destination?.route == Screens.Profil.screen
    Scaffold(
        topBar = {
            if(!isLogin && !isNotification)
                 HeaderComponent( navController = navController,username = authViewModel.currentUser?.data?.username.toString())
        },
        bottomBar = {
            if (!isLogin && !isNotification) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 20.dp)

                )

                {
                    IconButton(onClick = {
                        navController.navigate(Screens.Home.screen)
                    }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "",

                            tint = if (isHome) Color.Blue else Color.Black
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate(Screens.TabsHistory.screen) }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.riwayat),
                            contentDescription = "",
                            tint = if (isHistory) Color.Blue else Color.Black,
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.so),
                        contentDescription = "",
                        modifier = Modifier
                            .size(65.dp)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                            .clickable {
                                navController.navigate(Screens.Sos.screen)
                            }
                    )




                    IconButton(onClick = { navController.navigate((Screens.TabsEducation.screen)) }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.education),
                            contentDescription = "",
                            tint = if (isEducation) Color.Blue else Color.Black
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screens.Profil.screen) }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "",
                            tint = if (isProfile) Color.Blue else Color.Black
                        )
                    }

                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = defaultDestination.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) { Home(navController = navController) }
            composable(Screens.History.screen) { History(navController = navController) }
            composable(Screens.Education.screen) { Education(navController = navController) }
            composable(Screens.Profil.screen) {
                    val profileViewModel = viewModel(modelClass = ProfileViewModel::class.java)
                    Profil(navController = navController, profileViewModel = profileViewModel)
                }
            composable(Screens.Sos.screen) { Sos(navController = navController) }
            composable(Screens.Register.screen) {
                val registerViewModel = viewModel(modelClass = AuthViewModel::class.java)
                Register(navController = navController,registerViewModel=registerViewModel)
            }
            composable(Screens.Login.screen) {
                val loginViewModel = viewModel(modelClass = AuthViewModel::class.java)
                Login(navController = navController,loginViewModel=loginViewModel)
            }
            composable(Screens.Forget_Password.screen) { Forget_Password(navController = navController) }
            composable(Screens.Goggle.screen) { Goggle(navController = navController) }
            composable(Screens.Chatbot.screen){ Chatbot(navController = navController) }
            composable(Screens.UrgentCall.screen){ UrgentCall(navController = navController) }
            composable(Screens.Location.screen){ Location(navController = navController)}
            composable(Screens.TabsHistory.screen){ TabsHistory(navController = navController) }
            composable(Screens.Hazard_Maps.screen){ HazardMaps(navController = navController)}
            composable(Screens.TabsEducation.screen){ TabsEducation(navController = navController) }
            composable(Screens.Sosialization.screen){ Sosialization(navController = navController) }
            composable(Screens.Register_Sosialization.screen){ Register_Sosialization(navController = navController) }
            composable(Screens.Final_Sosialization.screen){ Final_Sosialization(navController = navController) }
            composable(Screens.Alarm.screen){ Alarm(navController = navController)}
            composable(Screens.History_Detail.screen){ History_Detail(navController = navController) }
            composable(Screens.TabsDonation.screen){ TabsDonation(navController = navController) }
            composable(Screens.Payment.screen){ backStackEntry ->
                val donationId = backStackEntry.arguments?.getString("donationId")

                Payment(navController = navController,donationId=donationId.toString())
            }
            composable(Screens.ReportDonation.screen){ ReportDonation(navController = navController) }
            composable(Screens.Notif.screen){ Notif(navController = navController) }
        }

    }
}
@Composable
fun NavigationRoute(paddingValues: PaddingValues = PaddingValues(0.dp),navController: NavController){


}

@Preview
@Composable
fun PreviewNavigation(){
    Navigation(Screens.Home)
}

@Composable
fun HeadingTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
    )
}


@Composable
fun HeaderComponent(navController: NavController,username:String?=null){
    Box(
        Modifier
            .background(Color(2, 125, 180, 228))
            .fillMaxWidth()){


            Row(
                horizontalArrangement= Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Row{

                Icon(
                    painter = painterResource(id = R.drawable.human),
                    contentDescription = "User Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 5.dp)
                        .offset(y = 8.dp)
                )
                Text(
                    text = "Hai "+username,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp),
                    style=TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize=18.sp
                    )
                )
                }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){

                Icon(
                    painter = painterResource(id = R.drawable.donation),
                    contentDescription = "Donation Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 5.dp)
                        .clickable { navController.navigate(Screens.TabsDonation.screen) }
                )
                Icon(
                    painter = painterResource(id = R.drawable.customer),
                    contentDescription = "Customer Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 5.dp)
                )
            }
            }

        }
}

