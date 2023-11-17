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
import com.example.child_helper.client.Client
import com.example.child_helper.databinding.ActivityFpactivityBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
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
                    val resizeFinger1 = Bitmap.createScaledBitmap(person.fingers[1], 400,400,false)
                    val resizeFinger2 = Bitmap.createScaledBitmap(person.fingers[2], 400,400,false)
                    val resizeFinger3 = Bitmap.createScaledBitmap(person.fingers[3], 400,400,false)
                    val resizeFinger4 = Bitmap.createScaledBitmap(person.fingers[4], 400,400,false)

                    val finger1: String =
                        bitmapToBas64(resizeFinger1)
                    val finger2: String =
                        bitmapToBas64(resizeFinger2)
                    val finger3: String =
                        bitmapToBas64(resizeFinger3)
                    val finger4: String =
                        bitmapToBas64(resizeFinger4)

                    Log.d("손가락1", finger1)
                    Log.d("손가락2", finger2)
                    Log.d("손가락3", finger3)
                    Log.d("손가락4", finger4)
                    thread(true){
                        //Client_imageoutput_test(this, "d안녕!!!!!")
                        val testmap1 = Client(this, "Input_Image", finger1)!!
                        val testmap2 = Client(this, "Input_Image1", finger2)!!
                        val testmap3 = Client(this, "Input_Image2", finger3)!!
                        val testmap4 = Client(this, "Input_Image3", finger4)!!
                        Log.d("변경 후", testmap1)
                        runOnUiThread {
                            binding.finger1.setImageBitmap(base64ToBitmap(testmap1))
                            binding.finger1A.setImageBitmap(base64ToBitmap(testmap2))
                            binding.finger2.setImageBitmap(base64ToBitmap(testmap3))
                            binding.finger2A.setImageBitmap(base64ToBitmap(testmap4))
                        }
                        Log.d("변경 후", "출력")
                    }

                    // Instantiate Enroll request
                    val enrollPersonRequest = EnrollPersonRequest(
                        PersonRequest(
                            "your-customID",
                            listOf(
                                FingerPersonRequest(
                                    finger1,
                                    finger2,
                                    finger3,
                                    finger4
                                )
                            )
                        )
                    )
//                    val testmap = base64ToBitmap(finger1)!!
//                    binding.finger1.setImageBitmap(testmap)
//                    binding.finger1A.setImageBitmap(base64ToBitmap(finger2)!!)
//                    binding.finger2.setImageBitmap(base64ToBitmap(finger3)!!)
//                    binding.finger2A.setImageBitmap(base64ToBitmap(finger4)!!)
                    //Log.d("사진 크기","${testmap.width}, ${testmap.height}")
                    //binding.finger1.setImageBitmap(testmap)

//
//                    // Get retrofit
//                    val retrofit = retrofitInstance
//
//                    // Execute request to the BioPass ID API
//                    val callback: Call<EnrollPersonResponse?>? =
//                        retrofit.enrollPerson(enrollPersonRequest)
//
//                    // Handle API response
//                    callback!!.enqueue(object : Callback<EnrollPersonResponse?> {
//                        override fun onResponse(
//                            call: Call<EnrollPersonResponse?>,
//                            response: Response<EnrollPersonResponse?>
//                        ) {
//                            Log.e(
//                                "에러 사람을 확인하려고 했으나 실패했음 ",
//                                response.body().toString()
//                            )
//                        }
//
//                        override fun onFailure(call: Call<EnrollPersonResponse?>, t: Throwable) {
//                            Log.d("사람파일 정보: ", t.message.toString())
//                        }
//                    })
                }
            }
        }
        // Start CameraActivity
        binding.fingerBtn.setOnClickListener { _ ->
            val cameraIntent = Intent(this, FingerprintCameraView::class.java)
            resultLauncher.launch(cameraIntent)

        }
        //fingerBtn.setOnClickListener {
         //   var intent = Intent(applicationContext, CVActivity::class.java)
          //  startActivity(intent)
        //}
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

