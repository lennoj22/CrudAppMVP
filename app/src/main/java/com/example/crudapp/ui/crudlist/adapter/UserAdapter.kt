package com.example.crudapp.ui.crudlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.models.list.Data

class UserAdapter (private val data: List<Data>,
                   private val onItemClick: (Data) -> Unit)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]
        holder.name.text = "${user.firstName} ${user.lastName} ${user.email}"
        holder.itemView.setOnClickListener {
            onItemClick(user)
        }
    }

    override fun getItemCount(): Int = data.size
}