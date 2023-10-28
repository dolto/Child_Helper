package com.example.child_helper

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.child_helper.Network.retrofitInstance
import com.example.child_helper.databinding.ActivityFpactivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.Arrays
import java.util.Base64


class FPActivity : AppCompatActivity() {
    private val TAG = "Demo_App"
    private lateinit var binding: ActivityFpactivityBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fpactivity)
        title = "지문 인식";

        val resultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                if (!person.fingers.isEmpty()) {
                    // Encode Bitmap to base64 string
                    val finger1: String =
                        bitmapToBas64(person.fingers[0])
                    val finger2: String =
                        bitmapToBas64(person.fingers[1])
                    val finger3: String =
                        bitmapToBas64(person.fingers[2])
                    val finger4: String =
                        bitmapToBas64(person.fingers[3])

                    // Instantiate Enroll request
                    val enrollPersonRequest = EnrollPersonRequest(
                        PersonRequest(
                            "your-customID",
                            Arrays.asList(
                                FingerPersonRequest(
                                    finger1,
                                    finger2,
                                    finger3,
                                    finger4
                                )
                            )
                        )
                    )

                    // Get retrofit
                    val retrofit = retrofitInstance

                    // Execute request to the BioPass ID API
                    val callback: Call<EnrollPersonResponse?>? =
                        retrofit.enrollPerson(enrollPersonRequest)

                    // Handle API response
                    callback!!.enqueue(object : Callback<EnrollPersonResponse?> {
                        override fun onResponse(
                            call: Call<EnrollPersonResponse?>,
                            response: Response<EnrollPersonResponse?>
                        ) {
                            Log.e(
                                TAG,
                                "Error trying to call enroll person. " + response.body()
                            )
                        }

                        override fun onFailure(call: Call<EnrollPersonResponse?>, t: Throwable) {
                            Log.d(TAG, "EnrollPersonResponse: " + t.message)
                        }
                    })
                }
            }
        }
        // Start CameraActivity
        binding.fingerBtn.setOnClickListener { _ ->
            val intent = Intent(this, FingerprintCameraView::class.java)
            resultLauncher.launch(intent)
        }
        //fingerBtn.setOnClickListener {
         //   var intent = Intent(applicationContext, CVActivity::class.java)
          //  startActivity(intent)
        //}
    }

    // Method to assist converting Bitmap to Base64 string
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

