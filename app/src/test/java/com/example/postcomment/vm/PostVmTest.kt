package com.example.postcomment.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postcomment.uc.PostUc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostVmTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var postUc: PostUc


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun test_get_post_comments() = runTest {
        Mockito.`when`(postUc.fetchPostComment()).thenReturn(emptyList())
        val sut = PostVm(postUc)
        sut.fetchPostWithComments()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.postCommentList.getOrAwaitValue()
        Assert.assertEquals(0, result.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}