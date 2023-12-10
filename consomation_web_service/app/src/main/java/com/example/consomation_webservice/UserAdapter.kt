package com.example.consomation_webservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface UserClickListener {
    fun onUserSaveClick(user: User)
    fun viewUser()
}
class UserAdapter(private val userList: List<User>,private val userClickListener: UserClickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        val saveButton: Button = itemView.findViewById(R.id.saveButton)
        var btn=itemView.findViewById<Button>(R.id.savedButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item_layout, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.nameTextView.text = "Name: ${currentUser.name}"
        holder.usernameTextView.text = "Username: ${currentUser.username}"
        holder.emailTextView.text = "Email: ${currentUser.email}"
        holder.saveButton.setOnClickListener {
            userClickListener.onUserSaveClick(currentUser)
        }
        holder.btn.setOnClickListener {
            userClickListener.viewUser()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
