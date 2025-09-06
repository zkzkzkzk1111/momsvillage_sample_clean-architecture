package com.ez.momsvill

import androidx.annotation.CallSuper
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MomsvillageApplication : MultiDexApplication() {
    val baseUrl: String
        get() = "API 주소"

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MomsvillageApplication
            private set

    }
}
