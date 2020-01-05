package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import androidx.annotation.StringRes
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionParams
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnErrorEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.speak.SpeakUtils

abstract class BaseRecognitionFragment: BaseFragment(){

    protected val recognitionUtils = RecognitionUtils()
    private var speakUtils: SpeakUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        context?.let { speakUtils = SpeakUtils(it) }
        super.onCreate(savedInstanceState)
    }

    override fun onMessageEvent(event: MessageEvent) {
        super.onMessageEvent(event)
        if(GlobalUtils.fragmentAlive == this@BaseRecognitionFragment.javaClass.name) {
            when (event) {
                is RecognitionOnResultEvent -> {
                    onRecognitionOnResultEvent(event)
                }

                is RecognitionOnErrorEvent -> {
                    onRecognitionOnErrorEvent(event)
                }
            }
        }
    }

    abstract fun onRecognitionOnResultEvent(event: RecognitionOnResultEvent)

    abstract fun onRecognitionOnErrorEvent(event: RecognitionOnErrorEvent)

    protected fun speak(@StringRes stringRes: Int, onSpeakDone:(() -> Unit)? = null, params: Bundle? = null){
        activity?.runOnUiThread {
            speakUtils?.speak(getString(stringRes), onSpeakDone, params)
        }
    }

    fun startRecognition(recognitionParams: RecognitionParams? = null){
        activity?.runOnUiThread {
            recognitionUtils.startRecognition(recognitionParams)
        }
    }
}