package ru.itis.kpfu.homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class PhotoContract(private val mContext: Context) : ActivityResultContract<Intent, Bitmap?>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bitmap? {
        return when(intent?.action) {
            "inline-data" -> {
                intent.takeIf { resultCode == Activity.RESULT_OK }?.getParcelableExtra("data")
            }
            else -> {
                val imageUri = intent.takeIf { resultCode == Activity.RESULT_OK }?.data
                MediaStore.Images.Media.getBitmap(mContext.contentResolver, imageUri)
            }
        }

    }
}

