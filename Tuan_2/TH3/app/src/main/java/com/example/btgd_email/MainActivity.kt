package com.example.btgd_email

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnCheck = findViewById<Button>(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            when {
                email.isEmpty() -> {
                    tvResult.text = "Email không hợp lệ"
                    tvResult.setTextColor(getColor(android.R.color.holo_red_dark))
                }
                !email.contains("@") -> {
                    tvResult.text = "Email không đúng định dạng"
                    tvResult.setTextColor(getColor(android.R.color.holo_red_dark))
                }
                else -> {
                    tvResult.text = "Bạn đã nhập email hợp lệ"
                    tvResult.setTextColor(getColor(android.R.color.holo_green_dark))
                }
            }
        }
    }
}
