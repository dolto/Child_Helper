package com.example.child_helper

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.child_helper.databinding.ActivityFpactivityBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Base64


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
                    val finger1: String =
                        bitmapToBas64(person.fingers[1])
                    val finger2: String =
                        bitmapToBas64(person.fingers[2])
                    val finger3: String =
                        bitmapToBas64(person.fingers[3])
                    val finger4: String =
                        bitmapToBas64(person.fingers[4])

                    Log.d("손가락1", finger1)
                    Log.d("손가락2", finger2)
                    Log.d("손가락3", finger3)
                    Log.d("손가락4", finger4)


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

                    binding.finger1.setImageBitmap(base64ToBitmap(finger1)!!)
                    binding.finger1A.setImageBitmap(base64ToBitmap(finger2)!!)
                    binding.finger2.setImageBitmap(base64ToBitmap(finger3)!!)
                    binding.finger2A.setImageBitmap(base64ToBitmap(finger4)!!)
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
    private fun grayScale(orgBitmap: Bitmap): Bitmap? {
        Log.i("gray", "in")
        val width: Int
        val height: Int
        width = orgBitmap.width
        height = orgBitmap.height
        val bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get pixel color
                pixel = orgBitmap.getPixel(x, y)
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)
                var gray = (0.2989 * R + 0.5870 * G + 0.1140 * B).toInt()

                // use 128 as threshold, above -> white, below -> black
                gray = if (gray > 128) 255 else 0
                // set new pixel color to output bitmap
                bmpGrayScale.setPixel(x, y, Color.argb(A, gray, gray, gray))
            }
        }
        return bmpGrayScale
    }

}

