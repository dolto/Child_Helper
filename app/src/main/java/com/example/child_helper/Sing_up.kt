package com.example.child_helper

import SharedPrefManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.child_helper.Data.LoginData
import com.example.child_helper.client.Client
import com.example.child_helper.MainActivity
import com.google.gson.Gson
import kotlin.concurrent.thread

class Sing_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val singup = findViewById<Button>(R.id.Btn_Sing_up);

        singup.setOnClickListener {
            val username = findViewById<EditText>(/* id = */ R.id.Sign_up_username).text.toString()
            val password = findViewById<EditText>(R.id.Sign_up_password).text.toString()
            val check = findViewById<EditText>(R.id.Check_password).text.toString()

            if(password == check){
                val loginData = LoginData(username, password)
                val login_info = Gson().toJson(loginData)
                thread(true) {
                    val result = Client(this, "Sign_up", login_info)
                    if (result == "회원가입 완료"){
                        val tokken = SharedPrefManager(this) // 'this'는 Context 객체입니다.
                        tokken.removeToken()
                        MainActivity().Login_page_visible()
                    }
                }
            }
            else{
                Toast.makeText(this, "비밀번호가 맞지 않습니다!", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}