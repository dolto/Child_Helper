package com.example.child_helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fpactivity)
        setTitle("지문 인식");

        var fingerBtn: View = findViewById(R.id.fingerBtn)
        fingerBtn.setOnClickListener {
            var intent = Intent(applicationContext, CVActivity::class.java)
            startActivity(intent)
        }
    }
}