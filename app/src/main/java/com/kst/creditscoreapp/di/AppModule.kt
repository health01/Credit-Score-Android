package com.kst.creditscoreapp.di

import com.google.gson.GsonBuilder
import com.kst.creditscoreapp.network.CreditService
import com.kst.creditscoreapp.repository.CreditRepository
import com.kst.creditscoreapp.repository.CreditRepositoryImpl
import com.kst.creditscoreapp.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(Constants.BASE_URL).build()

    @Singleton
    @Provides
    fun provideCreditService(retrofit: Retrofit): CreditService {
        return retrofit.create(CreditService::class.java)
    }

    @Singleton
    @Provides
    fun provideCreditRepository(creditService: CreditService): CreditRepository =
        CreditRepositoryImpl(creditService)
}