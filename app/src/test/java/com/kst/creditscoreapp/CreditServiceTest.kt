package com.kst.creditscoreapp

import com.google.gson.JsonSyntaxException
import com.kst.creditscoreapp.network.CreditService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class CreditServiceTest {
    private lateinit var service: CreditService
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreditService::class.java)
    }

    @After
    fun dropdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `retrieve correct DataSet`() {
        enqueueResponse("endpoint.json")
        val response = runBlocking {
            service.getDetail()
        }
        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/endpoint.json"))

        assertThat(response, `is`(notNullValue()))
        assertThat(response.accountIDVStatus, `is`(notNullValue()))
        assertThat(response.dashboardStatus, `is`(notNullValue()))
        assertThat(response.personaType, `is`(notNullValue()))
        assertThat(response.coachingSummary, `is`(notNullValue()))
        assertThat(response.augmentedCreditScore, equalTo(null))

        assertThat(response.accountIDVStatus, `is`("PASS"))

        assertThat(response.creditReportInfo?.score, `is`(514))
        assertThat(response.creditReportInfo?.maxScoreValue, `is`(700))
        assertThat(response.creditReportInfo?.hasEverBeenDelinquent, `is`(true))
    }

    @Test(expected = JsonSyntaxException::class)
    fun `retrieve IllegalStateException when api return array`() {
        enqueueResponse("endpointArray.json")


        runBlocking {
            service.getDetail()
        }

        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/endpointArray.json"))
    }

    private fun enqueueResponse(fileName: String) {
        val source = TestHelper.getJsonString(fileName)
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse
                .setBody(source)
        )
    }
}