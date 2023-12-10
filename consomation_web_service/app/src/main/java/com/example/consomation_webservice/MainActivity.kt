package com.example.consomation_webservice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle

import android.widget.Toast
import androidx.activity.ComponentActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity(),UserClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_main)

        var requestQueue = Volley.newRequestQueue(this)

        fetchDataFromWebService()
    }

    override fun viewUser() {
        val intent = Intent(this,SavedUser::class.java)
        startActivity(intent)
    }
    override fun onUserSaveClick(user: User) {
        saveUserToSharedPreferences(user)
    }
    private fun saveUserToSharedPreferences(user: User) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("SavedUsers", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()


        editor.putString("savedUserName", user.name)
        editor.putString("savedUserUsername", user.username)
        editor.putString("savedUserEmail", user.email)

        editor.apply()

        Toast.makeText(this, "User saved to SharedPreferences", Toast.LENGTH_SHORT).show()
    }


    private fun fetchDataFromWebService() {
        val url = "https://jsonplaceholder.typicode.com/users"

        var requestQueue = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONArray> { response ->
                // Process the JSON response
                parseJson(response)
            },
            Response.ErrorListener { error ->
                // Handle errors
               // textViewResult.text = "Error: ${error.message}"
            })

        requestQueue.add(jsonArrayRequest)
    }

    private fun parseJson(jsonArray: JSONArray) {
        val usersList = mutableListOf<User>()

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        var requestQueue = Volley.newRequestQueue(this)
        for (i in 0 until jsonArray.length()) {
            val userObject: JSONObject = jsonArray.getJSONObject(i)

            val name = userObject.getString("name")
            val username = userObject.getString("username")
            val email = userObject.getString("email")

            val user = User(name, username, email)
            usersList.add(user)
        }
        // Create and set the adapter
        val adapter = UserAdapter(usersList,this)
        recyclerView.adapter = adapter

    }
}



