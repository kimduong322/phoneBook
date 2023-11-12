package com.duongdk.hust.it4785.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            title = "ContactApp"
        }
        val phones = createFakePhoneData()
        val listPhone = findViewById<RecyclerView>(R.id.listPhone)
        listPhone.layoutManager = LinearLayoutManager(this)
        listPhone.adapter = phoneListAdapter(phones, object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailPhoneActivity::class.java)
                val clickedItem = phones[position]
                intent.putExtra("id", clickedItem.id.toString())
                intent.putExtra("hoTen", clickedItem.hoTen)
                intent.putExtra("soDienThoai", clickedItem.soDienThoai)
                intent.putExtra("email", clickedItem.email)
                startActivity(intent)
            }

        })

        // context menu
        registerForContextMenu(listPhone)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_call -> {
                Toast.makeText(this, "Calling...", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_sms -> {
                Toast.makeText(this, "Sending...", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_email -> {
                Toast.makeText(this, "Sending...", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }
}