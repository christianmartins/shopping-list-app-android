package br.com.shoppinglistapp.extensions

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.asCoroutineDispatcher

fun Dispatchers.getMainDispacherCustom() = Handler(Looper.getMainLooper()).asCoroutineDispatcher()