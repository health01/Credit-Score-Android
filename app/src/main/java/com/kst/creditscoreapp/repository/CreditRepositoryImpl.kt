package com.kst.creditscoreapp.repository

import com.kst.creditscoreapp.network.model.Credit
import com.kst.creditscoreapp.network.CreditService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(private val creditApi: CreditService) :
    CreditRepository {
    override fun getDetailFlow(): Flow<Credit> = flow<Credit> {
        emit(creditApi.getDetail())
    }
}