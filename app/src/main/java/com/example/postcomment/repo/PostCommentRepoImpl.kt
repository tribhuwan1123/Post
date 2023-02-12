package com.example.postcomment.repo

import com.example.postcomment.model.Comment
import com.example.postcomment.model.Post
import com.example.postcomment.services.ApiService

class PostCommentRepoImpl(
    private val apiService: ApiService
) : PostCommentRepo {
    override suspend fun getPosts(): List<Post> {
        val response = apiService.getPosts()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        }
        return emptyList()
    }
    override suspend fun getComments(): List<Comment> {
        val response = apiService.getComments()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        }
        return emptyList()
    }
}