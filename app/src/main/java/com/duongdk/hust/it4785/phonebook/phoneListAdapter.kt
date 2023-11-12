package com.duongdk.hust.it4785.phonebook

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class phoneListAdapter(val phones: List<phoneModel>, val listener: OnItemClickListener) : RecyclerView.Adapter<phoneListAdapter.ViewHolder>() {
    val clickedPosition: Int = -1

    inner class ViewHolder(val row: View) : RecyclerView.ViewHolder(row),
        View.OnCreateContextMenuListener {
        init {
            row.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            // đăng ký ContextMenu cho mỗi row
            row.setOnCreateContextMenuListener(this)
        }
        val phoneAvatar = row.findViewById<TextView>(R.id.phoneAvatar)
        val phoneName = row.findViewById<TextView>(R.id.phoneName)
        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedPhone = phones[position]
//                menu?.apply {
//                    add(0, R.id.menu_call, 0, "Call")
//                    add(0, R.id.menu_email, 1, "Email")
//                    add(0, R.id.menu_sms, 2, "SMS")
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row : View = LayoutInflater.from(parent.context).inflate(R.layout.single_phone_layout, parent, false)
        return ViewHolder(row)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phone = phones[position]
        holder.phoneAvatar.text = phone.hoTen.substring(0, 1).uppercase()
        holder.phoneAvatar.background.setColorFilter(generateRandomColor(), PorterDuff.Mode.SRC_IN)
        holder.phoneName.text = phone.hoTen
    }
    private fun generateRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }
}