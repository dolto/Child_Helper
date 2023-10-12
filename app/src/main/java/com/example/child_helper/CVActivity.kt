package com.example.child_helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvactivity)
        setTitle("지문 인식");
    }
}