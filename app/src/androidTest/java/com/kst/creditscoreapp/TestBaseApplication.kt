package com.kst.creditscoreapp

import com.kst.creditscoreapp.di.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class TestBaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerTestAppComponent.builder().application(this).build()
    }
}