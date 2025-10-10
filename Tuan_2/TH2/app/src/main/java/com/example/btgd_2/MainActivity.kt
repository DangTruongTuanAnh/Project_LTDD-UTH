package com.example.btgd_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCheck.setOnClickListener {
            val name = edtName.text.toString()
            val age = edtAge.text.toString()
            if (name.isBlank() || age.isBlank()) {
                tvResult.text = "Vui lòng nhập đầy đủ thông tin!"
            } else {
                tvResult.text = "Xin chào $name, bạn $age tuổi!"
            }
        }
    }
}
