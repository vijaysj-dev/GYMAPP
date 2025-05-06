package com.example.learning_rate

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.learning_rate.databinding.ActivityMain3Binding

class permission_components {
    companion object {
        val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}

class Main3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var surface: PreviewView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Your ID from layout
        surface = findViewById(R.id.camsurface)

        val permissionManager = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var grantPermission = true
            permissions.forEach {
                if (it.key in permission_components.REQUIRED_PERMISSIONS && !it.value) {
                    grantPermission = false
                    Log.d("Permission", "Permission denied for: ${it.key}")
                }
            }

            if (grantPermission) {
                run_cam()
            } else {
                Toast.makeText(baseContext, "PLEASE ENABLE CAMERA PERMISSION", Toast.LENGTH_SHORT).show()
            }
        }

        permissionManager.launch(permission_components.REQUIRED_PERMISSIONS)
    }

    private fun run_cam() {
        val camFutureProvider = ProcessCameraProvider.getInstance(this)
        camFutureProvider.addListener({
            val camProvider: ProcessCameraProvider = camFutureProvider.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(surface.surfaceProvider)
                }

            val selectCamera = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                camProvider.unbindAll()
                camProvider.bindToLifecycle(this, selectCamera, preview)
                Log.d("Camera", "Camera preview started")
            } catch (e: Exception) {
                Log.e("Camera", "Failed to bind camera: ${e.message}")
                Toast.makeText(this, "Failed to start camera", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(this))
    }
}
