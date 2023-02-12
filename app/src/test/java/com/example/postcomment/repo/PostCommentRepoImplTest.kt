package com.example.postcomment.repo

import com.example.postcomment.model.Post
import com.example.postcomment.services.ApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PostCommentRepoImplTest {

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetCommentsEmptyList() = runTest {
        Mockito.`when`(apiService.getPosts()).thenReturn(Response.success(emptyList()))
        val repo = PostCommentRepoImpl(apiService)
        val result = repo.getPosts()
        assertEquals(0, result.size)
    }

    @Test
    fun testGetCommentsExpectedList() = runTest {
        val list = listOf(
            Post(userId = 1, title = "Test 1", id = 1, body = "body"),
            Post(userId = 2, title = "Test 2", id = 2, body = "body 2"),
        )
        Mockito.`when`(apiService.getPosts()).thenReturn(Response.success(list))
        val repo = PostCommentRepoImpl(apiService)
        val result = repo.getPosts()
        assertEquals(2, result.size)
        assertEquals("Test 1", result[0].title)
    }

}