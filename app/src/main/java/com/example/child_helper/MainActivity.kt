package com.example.child_helper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.child_helper.databinding.ActivityRegisterBinding
import com.example.child_helper.databinding.MainPageBinding
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var binding :MainPageBinding
    private lateinit var binding2:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.main_page)
        title = "메인 페이지";
        Log.d("펄슨 선언 되었나?", person.toString())

        binding.BtnHelp.setOnClickListener{
            val  intent = Intent(applicationContext, FPActivity::class.java)
            startActivity(intent)
        }

        binding.BtnRegister.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }



}