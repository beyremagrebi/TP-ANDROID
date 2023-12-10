package com.example.tp_sqllite

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.example.tp_sqllite.ui.theme.EtudiantDBHelper

class MainActivity : ComponentActivity() {

    private lateinit var editTextNom: EditText
    private lateinit var editTextPrenom: EditText
    private lateinit var editTextTel: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextLogin: EditText
    private lateinit var editTextMotDePasse: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_main)

        editTextNom = findViewById(R.id.editTextNom)
        editTextPrenom = findViewById(R.id.editTextPrenom)
        editTextTel = findViewById(R.id.editTextTel)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextLogin = findViewById(R.id.editTextLogin)
        editTextMotDePasse = findViewById(R.id.editTextMotDePasse)

        val buttonValider = findViewById<Button>(R.id.buttonValider)
        val buttonAnnuler = findViewById<Button>(R.id.buttonAnnuler)

        buttonValider.setOnClickListener {
            if (validate()) {
                val values = ContentValues().apply {
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, editTextNom.text.toString())
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM, editTextPrenom.text.toString())
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE, editTextTel.text.toString())
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL, editTextEmail.text.toString())
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN, editTextLogin.text.toString())
                    put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP, editTextMotDePasse.text.toString())
                }

                val mDbHelper = EtudiantDBHelper(applicationContext)
                val db = mDbHelper.writableDatabase
                db.insert(EtudiantBC.EtudiantEntry.TABLE_NAME, null, values)

                db.close()
                mDbHelper.close()
                val intent = Intent(this, StudentListActivity::class.java)
                startActivity(intent)
            } else {
                showAlertDialog()
            }
        }

        buttonAnnuler.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun validate(): Boolean {
        val fields = listOf(
            editTextNom,
            editTextPrenom,
            editTextTel,
            editTextEmail,
            editTextLogin,
            editTextMotDePasse
        )

        for (field in fields) {
            if (field.text.isNullOrBlank()) {
                return false
            }
        }

        return true
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setMessage("Les champs ne doivent pas être vide")
            .setTitle("Attention")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage("Voulez-vous vraiment annuler la saisie?")
            .setTitle("Confirmation")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("Oui") { _, _ ->
                clearFields()
            }
            .setNegativeButton("Non") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun clearFields() {
        val fields = listOf(
            editTextNom,
            editTextPrenom,
            editTextTel,
            editTextEmail,
            editTextLogin,
            editTextMotDePasse
        )

        for (field in fields) {
            field.text.clear()
        }

        Toast.makeText(this, "Saisie annulée", Toast.LENGTH_SHORT).show()
    }

    private fun insertStudentData() {
        val values = ContentValues().apply {
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, editTextNom.text.toString())
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM, editTextPrenom.text.toString())
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE, editTextTel.text.toString())
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL, editTextEmail.text.toString())
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN, editTextLogin.text.toString())
            put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP, editTextMotDePasse.text.toString())
        }

        val mDbHelper = EtudiantDBHelper(applicationContext)
        val db = mDbHelper.writableDatabase
        db.insert(EtudiantBC.EtudiantEntry.TABLE_NAME, null, values)

        db.close()
        mDbHelper.close()
    }
}
