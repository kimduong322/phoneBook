package com.duongdk.hust.it4785.phonebook

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_phone_activity)
        setSupportActionBar(findViewById(R.id.toolbar_detailView))
        supportActionBar?.apply {
            title = "Detail Information of Phone"
        }
        try {
            val id = intent.getStringExtra("id")
            val hoTen = intent.getStringExtra("hoTen")
            val soDienThoai = intent.getStringExtra("soDienThoai")
            val email = intent.getStringExtra("email")
            Log.v("TAG", "id = $id, hoTen = $hoTen, soDienThoai = $soDienThoai, email = $email")
            findViewById<TextView>(R.id.avatar_detail).text = hoTen?.substring(0, 1)?.uppercase()
            findViewById<TextView>(R.id.avatar_detail).background.setColorFilter(generateRandomColor(), PorterDuff.Mode.SRC_IN)
            findViewById<TextView>(R.id.name_detail).text = hoTen
            findViewById<TextView>(R.id.phoneNumbDetail).text = soDienThoai
            findViewById<TextView>(R.id.email_detail).text = email
        } catch (ex: Exception) {
            setResult(Activity.RESULT_CANCELED)
        }
    }
    private fun generateRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }
}