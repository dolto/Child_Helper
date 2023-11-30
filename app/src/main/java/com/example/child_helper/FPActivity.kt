package com.example.child_helper

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.child_helper.Data.FingerData
import com.example.child_helper.Data.Finger_singletone
import com.example.child_helper.Data.convertFingerListToJson
import com.example.child_helper.Data.test_json
import com.example.child_helper.client.Client
import com.example.child_helper.databinding.ActivityFpactivityBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.ref.Cleaner
import java.lang.reflect.Type
import java.util.Base64
import kotlin.concurrent.thread



class FPActivity : AppCompatActivity() {
    private val TAG = "Demo_App"
    private lateinit var binding: ActivityFpactivityBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fpactivity)
        title = "지문 인식";



        val resultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                Log.d("사람 인식 시도", "")
                if (person.fingers.isNotEmpty()) {
                    Log.d("사람 인식함", person.fingers[0].toString())
                    // Encode Bitmap to base64 string
                    val resizeFinger1 = Bitmap.createScaledBitmap(person.fingers[1], 400,400,true)
                    val resizeFinger2 = Bitmap.createScaledBitmap(person.fingers[2], 400,400,true)
                    val resizeFinger3 = Bitmap.createScaledBitmap(person.fingers[3], 400,400,true)
                    val resizeFinger4 = Bitmap.createScaledBitmap(person.fingers[4], 400,400,true)

                    Finger_singletone.finger1 = bitmapToBas64(resizeFinger1)
                    Finger_singletone.finger2 = bitmapToBas64(resizeFinger2)
                    Finger_singletone.finger3 = bitmapToBas64(resizeFinger3)
                    Finger_singletone.finger4 = bitmapToBas64(resizeFinger4)

                    val send_finger = FingerData(- 1 ,Finger_singletone.finger1, Finger_singletone.finger2, Finger_singletone.finger3, Finger_singletone.finger4)
                    val finger_json = convertFingerListToJson(send_finger)
                    thread(true){
                        test_json.test1 = Client(this,"Try_Login","$finger_json")!!
                        Log.d("", "Json : 받아온 문자열 =" + test_json.test1)
                        go_CVA()
                        finish()
                    }
                }
            }
        }
        // Start CameraActivity
        binding.fingerBtn.setOnClickListener { _ ->
            val cameraIntent = Intent(this, FingerprintCameraView::class.java)
            resultLauncher.launch(cameraIntent)
        }
//        binding.fingerBtn.setOnClickListener {
//            var intent = Intent(this, CVActivity::class.java)
//            startActivity(intent)
    }


    fun go_CVA() {
        val intent = Intent(applicationContext, CVActivity::class.java)
        startActivity(intent)
    }

    // Method to assist converting Bitmap to Base64 string
    @RequiresApi(Build.VERSION_CODES.O)
    private fun base64ToBitmap(string: String): Bitmap? {
        return try{
            val base64decoder = Base64.getDecoder()
            val data = base64decoder.decode(string)
            BitmapFactory.decodeByteArray(data, 0, data.size)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bitmapToBas64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val base64encoder = Base64.getEncoder();
        val byteArray = stream.toByteArray()
        try {
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return base64encoder.encodeToString(byteArray);
        //return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

}

