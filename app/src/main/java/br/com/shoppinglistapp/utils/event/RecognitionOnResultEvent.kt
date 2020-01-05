package br.com.shoppinglistapp.utils.event

import br.com.shoppinglistapp.utils.RecognitionParams

class RecognitionOnResultEvent(
    val bestResult: String,
    val recognitionParams: RecognitionParams?
): MessageEvent()
