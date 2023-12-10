package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
data class Gouvernorat(val name: String, val imageResId: Int)
class MyAdapter(private val myDataSet: ArrayList<String>,private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val vh = LayoutInflater.from(parent.context).inflate(R.layout.ligne, parent,false)
        return ViewHolder(vh)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var current = myDataSet[position]
        holder.vText.text = current.toString()
    }

    override fun getItemCount(): Int {
        return myDataSet.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val vText: TextView = itemView.findViewById(R.id.word)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

            val position =adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }





}

