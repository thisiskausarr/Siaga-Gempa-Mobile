package com.example.siaga.ui.screen

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.webkit.WebViewClientCompat
import com.example.siaga.R
import com.example.siaga.ui.component.JavaScriptInterface
import com.example.siaga.ui.component.setupWebView

data class Message(
    val text: String,
    val isUser: Boolean
)
@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chatbot(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        // Initialize WebView and setup its configurations
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webChromeClient = WebChromeClient()
                    loadUrl("file:///android_asset/chat.html")
                    addJavascriptInterface(JavaScriptInterface(context), "AndroidFunction")

                    // Use WebViewClientCompat to handle page loading and errors
                    webViewClient = object : WebViewClientCompat() {
                        override fun onReceivedError(
                            view: WebView?,
                            errorCode: Int,
                            description: String?,
                            failingUrl: String?
                        ) {
                            super.onReceivedError(view, errorCode, description, failingUrl)
                            // Display error toast when page loading fails
                            Toast.makeText(
                                context,
                                "Error loading page: $description",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Placeholder for displaying messages from JavaScript
        val messageFromJS = "" // Implement logic to handle messages from JavaScript

        if (messageFromJS.isNotEmpty()) {
            Text("Message from JS: $messageFromJS")
        }
    }
}

//    var messages by remember { mutableStateOf(listOf<Message>(
//        Message("halo",false),
//        Message("aa",true),
//
//    )) }
//    var input by remember { mutableStateOf(TextFieldValue("")) }

//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        LazyColumn(
//            modifier = Modifier.weight(1f),
//            reverseLayout = true,
//        ) {
//            items(messages.reversed()) { message ->
//                MessageBubble(message = message)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Row(modifier = Modifier.fillMaxWidth()) {
//            TextField(
//                value = input,
//                onValueChange = { input = it },
//                placeholder = { Text("Type a message...") },
//                modifier = Modifier.weight(1f).border(width = 1.dp, color = Color.Red, shape = CircleShape),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.White,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//
//                ),
//                shape = RoundedCornerShape(8.dp)
//            )
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Button(
//
//                onClick = {
//                    if (input.text.isNotBlank()) {
//                        messages = messages + Message(input.text, true)
//                        input = TextFieldValue("")
//                        // Here you can add logic to send the user's message to your chatbot backend
//                        // and add the chatbot's response to the messages list.
//                        messages = messages + Message("Bot: ${input.text}", false) // Placeholder for bot response
//                    }
//                },
//                colors=ButtonDefaults.filledTonalButtonColors(Color.Gray),
//                modifier = Modifier.align(Alignment.CenterVertically),
//
//            ) {
//                Icon(
//                    modifier = Modifier.size(20.dp),
//                    painter = painterResource(id = R.drawable.send),
//                    contentDescription = "Send Icon",
//                    tint = Color.Red,
//
//                )
//            }
//        }
//    }

@Composable
fun MessageBubble(message: Message) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Text(
            text = message.text,
            fontSize = 16.sp,
            color = if (message.isUser) Color.White else Color.Black,
            modifier = Modifier
                .background(
                    color = if (message.isUser) Color.Red else Color(0xFFF1D6D6),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ChatbotPreview(){
    Chatbot(navController = rememberNavController())
}
