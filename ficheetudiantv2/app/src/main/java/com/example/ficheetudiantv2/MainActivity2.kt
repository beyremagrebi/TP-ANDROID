package com.example.ficheetudiantv2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity



class MainActivity2 : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)
        val nom=intent.getStringExtra("nom")
        val email=intent.getStringExtra("email")
        val classe=intent.getStringExtra("classe")

        val textnom=findViewById<TextView>(R.id.textView4)
        val textemail=findViewById<TextView>(R.id.textView5)
        val textclasse=findViewById<TextView>(R.id.textView6)
        val btn=findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        textnom.text=nom
        textemail.text=email
        textclasse.text=classe

    }
}




