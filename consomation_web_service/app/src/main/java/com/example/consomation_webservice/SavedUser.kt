package com.example.consomation_webservice

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.consomation_webservice.ui.theme.Consomation_webServiceTheme

class SavedUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_user)
        readUserFromSharedPreferences()
    }

    private fun readUserFromSharedPreferences(): User {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("SavedUsers", Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("savedUserName", "") ?: ""
        val username = sharedPreferences.getString("savedUserUsername", "") ?: ""
        val email = sharedPreferences.getString("savedUserEmail", "") ?: ""


        val userDataTextView: TextView = findViewById(R.id.userDataTextView)
        userDataTextView.text = "Name: $name\nUsername: $username\nEmail: $email"
        return User(name, username, email)
    }
}