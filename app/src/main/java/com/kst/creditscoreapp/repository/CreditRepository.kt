package com.kst.creditscoreapp.repository

import com.kst.creditscoreapp.network.model.Credit
import kotlinx.coroutines.flow.Flow

interface CreditRepository {
    fun getDetailFlow(): Flow<Credit>
}