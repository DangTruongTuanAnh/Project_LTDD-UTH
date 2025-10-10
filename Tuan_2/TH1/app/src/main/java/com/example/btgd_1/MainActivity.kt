package com.example.btgd_1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val btnCreate = findViewById<Button>(R.id.btnCreate)
        val layoutButtons = findViewById<LinearLayout>(R.id.layoutButtons)

        btnCreate.setOnClickListener {
            val input = edtNumber.text.toString().trim()
            layoutButtons.removeAllViews()

            // Kiểm tra dữ liệu trống
            if (input.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập vào số lượng!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kiểm tra xem có phải là số không
            val number = input.toIntOrNull()
            if (number == null) {
                Toast.makeText(this, "Bạn phải nhập SỐ, không được nhập chữ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kiểm tra giá trị âm hoặc 0
            if (number <= 0) {
                Toast.makeText(this, "Vui lòng nhập số lớn hơn 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tạo các nút
            for (i in 1..number) {
                val btn = Button(this)
                btn.text = i.toString()

                // Bo tròn và màu nền
                btn.setBackgroundResource(R.drawable.rounded_button)
                btn.setTextColor(Color.WHITE)
                btn.setPadding(0, 24, 0, 24)

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 16, 0, 0)
                btn.layoutParams = params

                btn.setOnClickListener {
                    Toast.makeText(this, "Bạn bấm nút $i", Toast.LENGTH_SHORT).show()
                }

                layoutButtons.addView(btn)
            }
        }
    }
}
