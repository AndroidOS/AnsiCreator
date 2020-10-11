package com.manuelcarvalho.ansicreator.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData

private const val TAG = "AppViewModel"

class AppViewModel(application: Application) : BaseViewModel(application) {

    val ImageArray = MutableLiveData<Array<Array<Int>>>()
    
}