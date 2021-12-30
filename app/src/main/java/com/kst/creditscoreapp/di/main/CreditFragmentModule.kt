package com.kst.creditscoreapp.di.main

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class CreditFragmentModule {
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO
}