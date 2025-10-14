package com.example.vasilkhusainovtest.ui.webViewScreen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            factory = { context ->

                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            url: String?
                        ): Boolean {
                            if (url != null) {
                                view?.loadUrl(url)
                            }
                            return true
                        }
                    }
                }
            },
            update = { webView ->
                webView.loadUrl(url.toString())
            }
        )
    }
}