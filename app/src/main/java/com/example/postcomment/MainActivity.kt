package com.example.postcomment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.postcomment.vm.PostVm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val postVm: PostVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postVm.fetchPostWithComments()
        setUpObservers()
    }

    private fun setUpObservers() {
//        lifecycleScope.launchWhenStarted {
//            postVm.postCommentList.collectLatest {
//                it.forEach { post ->
//                    tv.text = post.post?.title
//                }
//            }
//        }
    }

}