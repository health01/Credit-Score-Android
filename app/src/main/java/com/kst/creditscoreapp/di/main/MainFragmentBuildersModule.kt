package com.kst.creditscoreapp.di.main

import com.kst.creditscoreapp.ui.main.credit.CreditFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector(modules = [CreditFragmentModule::class])
    abstract fun contributeFirstFragment(): CreditFragment
}