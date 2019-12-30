package br.com.shoppinglistapp.utils.event

import br.com.shoppinglistapp.utils.ParamsCustom

class RecognitionOnResultEvent(
    val bestResult: String,
    val params: ParamsCustom?
): MessageEvent()
