package com.kst.creditscoreapp

import okio.Okio

object TestHelper {

    fun getJsonString(fileName: String = "endpoint.json"): String {
        val classloader = javaClass.classLoader
        val inputStream = classloader.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(Charsets.UTF_8)
    }
}