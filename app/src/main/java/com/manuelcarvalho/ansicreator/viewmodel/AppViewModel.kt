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
        var display = Array(80) { Array(25) { 0 } }
        var count = 0
        var pixsum = 0
        var average = 0
        for (y in 0..bitmap.height - 1) {
            for (x in 0..bitmap.width - 1) {
                val pix = bitmap.get(x, y)
                count += 1
                pixsum += pix
                //Log.d(TAG, "${pixsum}")
            }
        }
        average = pixsum / count
        var matX = 0
        var matY = 0
        for (y in 0..bitmap.height - 1 step (bitmap.height / 25)) {
            for (x in 0..bitmap.width - 1 step (bitmap.width / 80)) {
                val pix = bitmap.get(x, y)


                if (pix < -average) {
                    display[matX][matY] = 1
                    Log.d(TAG, "x ${matX} y ${matY}")
                }
                if ((matX > 78)) {
                    matX = 79
                } else {
                    matX += 1
                }
            }
            matX = 0
            if ((matY > 23)) {
                matY = 23
            } else {
                matY += 1
            }
            matY += 1
        }
        Log.d(TAG, "Pix average = ${average}")
        display[40][16] = 1
        display[70][22] = 1
        imageArray.value = display
    }

}