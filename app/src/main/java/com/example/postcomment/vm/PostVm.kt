package com.example.postcomment.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postcomment.model.PostComment
import com.example.postcomment.uc.PostUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostVm @Inject constructor(private val postUc: PostUc) : ViewModel() {

    private val _postCommentList = MutableLiveData<List<PostComment>>()
    val postCommentList: MutableLiveData<List<PostComment>>
        get() = _postCommentList

    fun fetchPostWithComments() {
        viewModelScope.launch {
            val postWithCommentList = postUc.fetchPostComment()
            _postCommentList.value = postWithCommentList
        }
    }
}