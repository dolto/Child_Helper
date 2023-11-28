package com.example.child_helper

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.biopassid.fingerprintsdk.engine.FingerprintCaptureListener
import br.com.biopassid.fingerprintsdk.engine.FingerprintCaptureState
import br.com.biopassid.fingerprintsdk.ui.view.FingerprintView
import com.example.child_helper.databinding.ActivityFingerprintCameraViewBinding


class FingerprintCameraView : AppCompatActivity() {
    private lateinit var fingerprintView: FingerprintView;
    private lateinit var binding: ActivityFingerprintCameraViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_fingerprint_camera_view)

        // Set listener to handle Fingerprint callback
        binding.fingerprintView.setCaptureListener(object : FingerprintCaptureListener {
            override fun onFingerCapture(images: List<Bitmap>) {
                // monitor and handle fingers capture

                Log.d("사람에 지문 추가", "Capture: " + images[0])
                person.fingers = images
                setResult(RESULT_OK)

            }

            override fun onStatusChanged(state: FingerprintCaptureState?) {
                // monitor and handle the current state of the capture
            }

            override fun onFingerDetected(rects: Array<Rect?>?) {
                // monitor and handle finger rects
            }


        }

        )

    }



}