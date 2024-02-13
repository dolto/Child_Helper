package com.example.child_helper

import SharedPrefManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.child_helper.Data.test_json
import com.example.child_helper.client.Client
import com.example.child_helper.databinding.ActivityRegisterBinding
import com.example.child_helper.databinding.MainPageBinding
import kotlin.concurrent.thread
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var binding :MainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.main_page)
        title = "메인 페이지";
        Log.d("펄슨 선언 되었나?", person.toString())
        val token = SharedPrefManager(this) // 'this'는 Context 객체입니다.
        Log.d("test", "서버 응답129")

        val UserID = token.getToken().toString()

        Login_page_visible()


        binding.BtnHelp.setOnClickListener{
            val  intent = Intent(applicationContext, FPActivity::class.java)
            startActivity(intent)
        }

        binding.BtnRegister.setOnClickListener {
            Log.d("","서버 응답 140")
            if (token.getToken().toString() != "user_token" && token.getToken().toString() != "null") {
                Handler(Looper.getMainLooper()).postDelayed({
                    thread(true){
                        Log.d("","서버 응답 141" + "$UserID")
                        test_json.test1 = Client(this, "User_List", "$UserID").toString()
                        Log.d("","서버 응답 142")
                        go_UserList()
                        finish()
                    }
                }, 100)
            } else {
                Toast.makeText(this, "로그인을 먼저 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.GoSingIn.setOnClickListener {
            val intent = Intent(applicationContext, Login_page::class.java)
            startActivity(intent)
        }
        binding.GoSingUp.setOnClickListener {
            val intent = Intent(applicationContext, Sing_up::class.java)
            startActivity(intent)
        }
        binding.LogOut.setOnClickListener {
            token.removeToken()
            Log.d("test", "서버 응답128" + token.getToken())
            Login_page_visible()
        }

//        thread(true){
//            Client(this,"test","test")
//        }
//        thread(true){
//            Client(this)
//        }
//        thread(true){
//            Client(this)
//        }
    }

    fun Login_page_visible() {
        val token = SharedPrefManager(this) // 'this'는 Context 객체입니다.
        Log.d("test", "서버 응답130" + token.getToken())

        val Log_off = findViewById<LinearLayout>(R.id.Log_off)
        val Log_on = findViewById<LinearLayout>(R.id.Log_on)

        if (token.getToken().toString() == "user_token" || token.getToken().toString() == "null") {
            Log_off.visibility = View.VISIBLE
            Log_on.visibility = View.GONE
        } else {
            Log_off.visibility = View.GONE
            Log_on.visibility = View.VISIBLE
        }
    }
    fun go_UserList() {
        val intent = Intent(applicationContext, Update_user_page::class.java)
        startActivity(intent)
    }
}


