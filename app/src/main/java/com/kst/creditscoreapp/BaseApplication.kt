package com.kst.creditscoreapp

import com.kst.creditscoreapp.di.AppComponent
import com.kst.creditscoreapp.di.DaggerAppComponent
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this).build()
    }
}