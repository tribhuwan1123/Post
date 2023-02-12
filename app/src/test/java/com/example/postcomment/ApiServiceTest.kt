package com.example.postcomment

import com.example.postcomment.services.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceTest {

    lateinit var mockWebServer: MockWebServer

    lateinit var apiService: ApiService


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService =
            Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService::class.java)
    }

    @Test
    fun getPostsEmpty() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)
        val response = apiService.getPosts()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun getPosts() = runTest {
        val mockResponse = MockResponse()

        val content = Helper.readFileResource("/posts.json")
        mockResponse.setBody(content)
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
        val response = apiService.getPosts()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(2, response.body()!!.size)
    }
    @Test
    fun getPostsError() = runTest {
        val mockResponse = MockResponse()

        mockResponse.setBody("Something went wrong")
        mockResponse.setResponseCode(404)
        mockWebServer.enqueue(mockResponse)
        val response = apiService.getPosts()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


}