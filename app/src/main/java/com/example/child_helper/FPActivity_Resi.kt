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
import com.example.child_helper.Data.Finger_singletone
import com.example.child_helper.client.Client
import com.example.child_helper.databinding.ActivityFpactivityBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Base64
import kotlin.concurrent.thread



class FPActivity_Resi : AppCompatActivity() {


    private val TAG = "Demo_App"
    private lateinit var binding: ActivityFpactivityBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("","Json : 만들어지긴 했음")
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fpactivity)
        title = "지문 인식";



        val resultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                if (person.fingers.isNotEmpty()) {
                    // Encode Bitmap to base64 string
                    Log.d("","Json : Finger")
                    val resizeFinger1 = Bitmap.createScaledBitmap(person.fingers[1], 400,400,false)
                    val resizeFinger2 = Bitmap.createScaledBitmap(person.fingers[2], 400,400,false)
                    val resizeFinger3 = Bitmap.createScaledBitmap(person.fingers[3], 400,400,false)
                    val resizeFinger4 = Bitmap.createScaledBitmap(person.fingers[4], 400,400,false)

                    Finger_singletone.finger1 = bitmapToBas64(resizeFinger1)
                    Finger_singletone.finger2 = bitmapToBas64(resizeFinger2)
                    Finger_singletone.finger3 = bitmapToBas64(resizeFinger3)
                    Finger_singletone.finger4 = bitmapToBas64(resizeFinger4)

                    Log.d("Json 손가락1", Finger_singletone.finger1)
                    Log.d("Json 손가락2", Finger_singletone.finger2)
                    Log.d("Json 손가락3", Finger_singletone.finger3)
                    Log.d("Json 손가락4", Finger_singletone.finger4)
                }
            }
            finish()
        }
        // Start CameraActivity
        binding.fingerBtn.setOnClickListener { _ ->
            val cameraIntent = Intent(this, FingerprintCameraView::class.java)
            resultLauncher.launch(cameraIntent)
        }
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

