package com.manuelcarvalho.ansicreator.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.get
import androidx.lifecycle.MutableLiveData

private const val TAG = "AppViewModel"

class AppViewModel(application: Application) : BaseViewModel(application) {

    val imageArray = MutableLiveData<Array<Array<Int>>>()

    fun decodeBitmap(bitmap: Bitmap) {
        for (y in 0..bitmap.height - 1) {
            for (x in 0..bitmap.width - 1) {
                val pix = bitmap.get(x, y)
                Log.d(TAG, "${pix}")
            }
        }
    }

}