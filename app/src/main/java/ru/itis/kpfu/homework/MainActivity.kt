package ru.itis.kpfu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import ru.itis.kpfu.homework.databinding.ActivityMainBinding
import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val multiplyPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val isCameraGranted = it[Manifest.permission.CAMERA]
        val isReadStorageGranted = it[Manifest.permission.READ_EXTERNAL_STORAGE]
        if (isCameraGranted != true || isReadStorageGranted != true) {
            finish()
        }
    }

    private val getPhoto = registerForActivityResult(PhotoContract(this)) { image ->
        binding?.ivPhoto?.let { imageView ->
            Glide.with(this)
                .load(image)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        multiplyPermissions.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))

        binding?.run {
            btLoadPhoto.setOnClickListener {
                intentTest()
            }

        }
    }

    private fun intentTest() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).takeIf {
            it.resolveActivity(this.packageManager) != null
        }

        val galleryIntent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }

        val chooser = Intent.createChooser(galleryIntent, "Load photo")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        getPhoto.launch(chooser)
    }

}