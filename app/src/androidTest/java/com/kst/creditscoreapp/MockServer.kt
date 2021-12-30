package com.kst.creditscoreapp

import com.kst.creditscoreapp.TestHelper
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServer {
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val source = TestHelper.getJsonString()
            val mockResponse = MockResponse()
            return mockResponse.setBody(source)
        }
    }
}