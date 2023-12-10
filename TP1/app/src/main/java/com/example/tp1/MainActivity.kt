package com.example.tp1

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
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
import com.example.tp1.ui.theme.TP1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var message:String
        val rdGroup=findViewById<RadioGroup>(R.id.RadioGroup)
        val btn=findViewById<Button>(R.id.button)
        val chk=findViewById<CheckBox>(R.id.checkBox)
        val chk2=findViewById<CheckBox>(R.id.checkBox2)
        btn.setOnClickListener({
            val selectbtn:Int=rdGroup!!.checkedRadioButtonId
            val btn=findViewById<RadioButton>(selectbtn)
            message="Spécialité "+btn.text
            if(chk.isChecked and !chk2.isChecked){
                message+=" club "+chk.text
            }
            else if(!chk.isChecked and chk2.isChecked){
                message+=" club "+chk2.text
            }
            else{
                message+=" club "+chk.text+" et "+chk2.text
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

    }



}