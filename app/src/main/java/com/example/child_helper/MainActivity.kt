package com.example.child_helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        setTitle("메인 페이지");

        val Btn_Help: View = findViewById(R.id.Btn_Help)
        val Btn_Register : View = findViewById(R.id.Btn_Register)
        Btn_Help.setOnClickListener{
            val  intent = Intent(applicationContext, FPActivity::class.java)
            startActivity(intent)


        }
        Btn_Register.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}