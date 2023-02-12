package com.example.postcomment.services

import com.example.postcomment.model.Comment
import com.example.postcomment.model.Post
import retrofit2.Response
import retrofit2.http.GET



//You may notice that instead of Call<T>, we now have a function with the suspend modifier defined in our interface function.
//
//According to Retrofit documentation this function will, behind the scenes behave as a normal Call.enqueue operation.
//
//Also we wrap our response in a Response object to get metadata about our request response e.g. information like response code.
//
//We no longer have to await() anymore as this is handled automatically! As with all networking on Android its done on the background. And this is a very clean way of doing so!

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/comments")
    suspend fun getComments(): Response<List<Comment>>

}