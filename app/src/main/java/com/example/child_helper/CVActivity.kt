package com.example.child_helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.child_helper.databinding.ActivityCvactivityBinding

class CVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvactivity)
        setTitle("지문 인식");
    }
}