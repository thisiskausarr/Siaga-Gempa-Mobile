package com.example.siaga.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.runtime.Composable

private lateinit var webView: WebView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun setupWebView(context: Context) {
    webView.settings.javaScriptEnabled = true
    webView.webChromeClient = WebChromeClient()
    webView.webViewClient = object : WebViewClient() {
        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            // Handle errors, including ERR_CLEARTEXT_NOT_PERMITTED
            Toast.makeText(
                context,
                "Error loading page: $description",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
@Composable
private fun loadChatHtml() {
    // Load the HTML file containing the chat interface from the assets directory
    webView.loadUrl("file:///android_asset/chat.html")
}