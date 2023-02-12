package com.example.postcomment.uc

import com.example.postcomment.model.PostComment
import com.example.postcomment.repo.PostCommentRepo
import kotlinx.coroutines.*
import javax.inject.Inject

class PostUc @Inject constructor(
    private val postCommentRepo: PostCommentRepo
) {
    suspend fun fetchPostComment(): List<PostComment> {
        val result = CoroutineScope(Dispatchers.IO).async {
            val postCommentList = ArrayList<PostComment>()
            val postsResponse = async {
                postCommentRepo.getPosts()
            }
            val commentsResponse = async {
                postCommentRepo.getComments()
            }
            val posts = postsResponse.await()
            val comments = commentsResponse.await()

            if (posts.isNotEmpty()) {
                for (post in posts) {
                    var postComment = PostComment()
                    postComment = postComment.copy(post = post)
                    if (comments.isNotEmpty()) {
                        val postRelatedComments = comments.filter { it.postId == post.id }
                        postComment = postComment.copy(comments = postRelatedComments)
                    }
                    postCommentList.add(postComment)
                }
            }
            postCommentList
        }
        return result.await()
    }
}