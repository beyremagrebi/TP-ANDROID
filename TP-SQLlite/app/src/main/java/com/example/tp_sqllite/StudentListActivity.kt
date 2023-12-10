package com.example.tp_sqllite

import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
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
import com.example.tp_sqllite.ui.theme.EtudiantDBHelper
import com.example.tp_sqllite.ui.theme.TPSQLLiteTheme

class StudentListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val listViewStudents: ListView = findViewById(R.id.listViewStudents)

        // Set the title
        textViewTitle.text = "Liste des étudiants"

        // Fetch the data from the database
        val dbHelper = EtudiantDBHelper(applicationContext)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL,
        )

        val cursor: Cursor = db.query(
            EtudiantBC.EtudiantEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val fromColumns = arrayOf(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM)
        val toViews = intArrayOf(android.R.id.text1) // The TextView in simple_list_item_1

        val adapter = SimpleCursorAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            cursor,
            fromColumns,
            toViews,
            0
        )

        listViewStudents.adapter = adapter


    }
}


