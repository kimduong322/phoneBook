package com.duongdk.hust.it4785.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val phones = createFakePhoneData()
    private lateinit var listPhone: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            title = "ContactApp"
        }

        listPhone = findViewById<RecyclerView>(R.id.listPhone)
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    fun handleAddContact() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Contact")

        val view = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        builder.setView(view)

        val editTextName = view.findViewById<EditText>(R.id.editTextName)
        val editTextPhoneNumber = view.findViewById<EditText>(R.id.editTextPhoneNumber)
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)

        builder.setPositiveButton("Add") { dialog, which ->
            val name = editTextName.text.toString().trim()
            val phoneNumber = editTextPhoneNumber.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty()) {
                // Generate a temporary ID (replace this with a proper ID generation method)
                val temporaryId = phones.size + 1

                // Create a new phoneModel with the entered information
                val newContact = phoneModel(temporaryId, name, phoneNumber, email)

                // Add the new contact to the phones list
                phones.add(newContact)

                // Notify the adapter that the data set has changed
                listPhone.adapter?.notifyDataSetChanged()

                // Optionally, you can save the updated phones list to a database or any other storage

                Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            // Dismiss the dialog when "Cancel" is clicked
            dialog.dismiss()
        }

        builder.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_contact_opt_menu -> {
                handleAddContact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}