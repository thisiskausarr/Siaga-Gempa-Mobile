package com.example.siaga

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siaga.navigation.Screens
import com.example.siaga.ui.component.Navigation
import com.example.siaga.ui.theme.SiagaTheme
import com.example.siaga.viewmodel.AlarmViewModel
import com.example.siaga.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    private lateinit var receiver: BroadcastReceiver

    companion object {

        public var defaultRoute: Screens = Screens.Login
        public fun ChangeRoute(route:Screens){
            defaultRoute=route
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == "com.example.ACTION_START_ACTIVITY") {
                    // Start your activity here
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        }
        registerReceiver(receiver, IntentFilter("com.example.ACTION_START_ACTIVITY"),
            RECEIVER_NOT_EXPORTED
        )

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.WAKE_LOCK,
                android.Manifest.permission.POST_NOTIFICATIONS,
                android.Manifest.permission.SYSTEM_ALERT_WINDOW
            ),
            101
        )
        setContent {
            SiagaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyApp(defaultRoute)
                }
            }
        }
    }
}

@Composable
fun MyApp(defaultRoute:Screens) {
    var defaultRoutes :Screens = defaultRoute
    val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
    val context = LocalContext.current
    authViewModel.getCurremtUser()

//    authViewModel.logoutAction(context)
    val isLogin = authViewModel.currentUser
    Log.d("TAG", "MyApp: ${isLogin.data?.auth_id}")
    if(isLogin.data?.auth_id.toString()!= "null" && defaultRoutes==Screens.Login) defaultRoutes = Screens.Home

    Navigation(defaultDestination = defaultRoutes)
}

