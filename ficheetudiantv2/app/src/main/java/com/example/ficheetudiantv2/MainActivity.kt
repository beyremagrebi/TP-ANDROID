package com.example.ficheetudiantv2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
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
import com.example.ficheetudiantv2.ui.theme.Ficheetudiantv2Theme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    var selected="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val classeSpinner = findViewById<Spinner>(R.id.spinner);
        val classes = resources.getStringArray(R.array.classe)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)

        // Spécifiez le style du menu déroulant
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Associez l'adaptateur au Spinner
        classeSpinner.adapter = adapter
        classeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Retrieve the selected item from the spinner
                val selectedItem = classes[position]
                selected=selectedItem
                // Do something with the selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do something when nothing is selected (optional)
                Toast.makeText(applicationContext, "Classe not selected", Toast.LENGTH_SHORT).show()
            }
        }
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val btn=findViewById<Button>(R.id.buttonShowDate)
        editTextDate.setOnClickListener{
            showDatePickerDialog(editTextDate)
        }
        btn.setOnClickListener {

            valider(it)


        }
    }

    fun valider(v: View?) {
        val nom = findViewById<EditText>(R.id.editTextText)
        val email=findViewById<EditText>(R.id.editTextEmail)
        val date = findViewById<EditText>(R.id.editTextDate)
        if (nom.text.length=== 0 || email.text.length === 0 || date.text.length===0) {
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
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nom", nom.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putExtra("classe", selected)
            startActivity(intent)
        }

    }
    private fun showDatePickerDialog(editTextDate:EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
                editTextDate.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}




