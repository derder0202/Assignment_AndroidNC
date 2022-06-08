package com.example.assignment_androidnc.news

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.assignment_androidnc.R
import com.example.assignment_androidnc.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding:ActivityWebViewBinding
    lateinit var webView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = binding.newsWebView

        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("linkNews").toString())
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
}