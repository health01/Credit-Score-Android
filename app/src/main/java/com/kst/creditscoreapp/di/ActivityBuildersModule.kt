package com.kst.creditscoreapp.di

import com.kst.creditscoreapp.di.main.MainFragmentBuildersModule
import com.kst.creditscoreapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun mainActivity(): MainActivity
}