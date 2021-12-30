package com.kst.creditscoreapp.network

import com.kst.creditscoreapp.model.LocalScoreDetail

sealed class CreditState {
    data class Error(var exception: Throwable? = NullPointerException()) : CreditState()
    data class Success(var localScoreDetail: LocalScoreDetail?) : CreditState()
}
