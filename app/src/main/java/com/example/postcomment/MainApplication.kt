package com.example.postcomment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp annotation generates Hilt code for Application class and creates parent container.

@HiltAndroidApp
class MainApplication : Application()