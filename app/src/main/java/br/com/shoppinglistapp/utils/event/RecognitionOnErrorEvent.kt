package br.com.shoppinglistapp.utils.event

import androidx.annotation.StringRes

class RecognitionOnErrorEvent(
    @StringRes
    val errorMessageStringRes: Int
): MessageEvent()
