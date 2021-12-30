package com.kst.creditscoreapp.mockData

import com.google.gson.Gson
import com.kst.creditscoreapp.TestHelper
import com.kst.creditscoreapp.network.model.Credit
import com.kst.creditscoreapp.network.CreditService

class MockCreditService(private val fileName: String) : CreditService {
    override suspend fun getDetail(): Credit {
        val source = TestHelper.getJsonString(fileName)
        val gson = Gson()
        return gson.fromJson(source, Credit::class.java)
    }
}