package com.example.tp3_dialog

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp3_dialog.ui.theme.TP3dialogTheme
import com.google.android.material.snackbar.Snackbar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)
        val webView = findViewById<WebView>(R.id.webview)

        // Configuration du WebView
        webView.webViewClient = WebViewClient() // Utilisez un WebViewClient pour gérer les chargements de pages
        webView.loadUrl("https://www.google.com") // Chargez l'URL souhaitée
        webView.settings.javaScriptEnabled = true // Activez les scripts JavaScript
        webView.settings.setSupportZoom(true) // Activez le zoom sur la page
        val validationButton = findViewById<Button>(R.id.button)
        validationButton.setOnClickListener {
            valider(it)

        }


    }

    fun valider(v: View?) {
        val nom = findViewById<EditText>(R.id.nom)
        val prenom = findViewById<EditText>(R.id.prenom)
        val email = findViewById<EditText>(R.id.email)

        if (nom.text.length===0 || prenom.text.length === 0 || email.text.length === 0) {
            val ad: AlertDialog.Builder
            ad = AlertDialog.Builder(this)
            ad.setMessage("Les champs ne doivent pas être vide")
            ad.setTitle("Error")
            ad.setIcon(android.R.drawable.btn_dialog)
            ad.setPositiveButton(
                "yes"
            ) { dialogInterface, i -> finish() }
            val a = ad.create()
            a.show()


        }
        else{
            val progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setTitle("Kotlin Progress Dialog")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog.setMessage("Downloading music, please wait")
            progressDialog.setIndeterminate(true)
            progressDialog.setProgress(0)
            progressDialog.show()
        }
    }
    inner class WebViewClient : android.webkit.WebViewClient() {

        // Charger l’URL
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }
        // Faire disparaître le progressBar après le chargement de la page
        override fun onPageFinished(view: WebView, url: String) {
            var progressBar = findViewById<ProgressBar>(R.id.progressBar)
            super.onPageFinished(view, url)
            progressBar.visibility=View.GONE

        }
    }
}
