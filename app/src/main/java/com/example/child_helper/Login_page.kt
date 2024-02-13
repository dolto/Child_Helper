package com.example.child_helper

import SharedPrefManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.child_helper.Data.LoginData
import com.example.child_helper.client.Client
import com.google.gson.Gson
import kotlin.concurrent.thread

class Login_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val login_btn = findViewById<Button>(R.id.login)




        login_btn.setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()

            val loginData = LoginData(username, password)
            val login_info = Gson().toJson(loginData)
            thread(true){

                val result = Client(this,"Sign_in","$login_info")
                Log.d("test", "서버 응답124" + "$result")
                if(result == "true"){
                    Log.d("test", "서버 응답125" + "$result")
                    val tokken = SharedPrefManager(this) // 'this'는 Context 객체입니다.
                    Log.d("test", "서버 응답126" + "$tokken")
                    tokken.saveToken(username)
                    Log.d("test", "서버 응답127" + "${tokken.getToken()}")
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Log.d("test", "서버 응답131")
                    Handler(Looper.getMainLooper()).postDelayed({
                        Toast.makeText(this, "아이디 혹은 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
                    }, 100)
                    Log.d("test", "서버 응답131")
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }



        }


    }
}