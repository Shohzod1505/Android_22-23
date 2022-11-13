package ru.itis.kpfu.homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.result.contract.ActivityResultContract

class PhotoContract : ActivityResultContract<Intent, Bitmap?>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Bitmap? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.getParcelableExtra("data")
    }
}

