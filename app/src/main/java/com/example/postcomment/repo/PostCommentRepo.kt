package com.example.postcomment.repo

import com.example.postcomment.model.Comment
import com.example.postcomment.model.Post

interface PostCommentRepo {
    suspend fun getPosts(): List<Post>
    suspend fun getComments(): List<Comment>
}