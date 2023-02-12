package com.example.postcomment.model

data class PostComment(
    val post: Post? = null, val comments: List<Comment>? = null
)