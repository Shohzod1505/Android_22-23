package ru.itis.kpfu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import ru.itis.kpfu.homework.databinding.ActivityMainBinding
import android.Manifest
import android.net.Uri
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val multiplyPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
//        val isCameraGranted = it[Manifest.permission.CAMERA]
//        val isReadStorageGranted = it[Manifest.permission.READ_EXTERNAL_STORAGE]
//        if (isCameraGranted == true && isReadStorageGranted == true) {
//
//        } else {
//
//        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { imageURI: Uri? ->
        binding?.ivPhoto?.let {
            Glide.with(this)
                .load(imageURI)
                .centerCrop()
                .into(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        multiplyPermissions.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))

//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).takeIf {
//            it.resolveActivity(this.packageManager) != null
//        }

//        val galleryIntent = Intent(Intent.ACTION_PICK).apply {
//            type = "image/*"
//        }

        binding?.run {

            btLoadPhoto.setOnClickListener {
//                cameraIntent()
                galleryIntent()
//                getContent.launch("image/*")
            }

        }

    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val chooserIntent = Intent.createChooser(intent, "Camera")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun galleryIntent() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }

        val chooserIntent = Intent.createChooser(intent, "Gallery")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


}