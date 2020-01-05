package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.annotation.StringRes
import br.com.shoppinglistapp.utils.DateUtils
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnErrorEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import java.util.*

abstract class BaseRecognitionFragment: BaseFragment(), TextToSpeech.OnInitListener {

    protected val recognitionUtils = RecognitionUtils()
    private lateinit var textToSpeech: TextToSpeech
    protected var hasStartToSpeech = false


    override fun onCreate(savedInstanceState: Bundle?) {
        textToSpeech = initTextToSpeech()
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

    private fun initTextToSpeech (): TextToSpeech {
        return TextToSpeech(context, this).let {
            it.language = Locale.getDefault()
            it.setSpeechRate(1.8F)
            it
        }
    }

    protected fun speak(string: String, params: Bundle? = null){
        speak((string as CharSequence), params)
    }

    protected fun speak(@StringRes stringRes: Int, params: Bundle? = null){
        speak(getString(stringRes), params)
    }

    private fun speak(charSequence: CharSequence, params: Bundle? = null){
        activity?.runOnUiThread {
            try{
                textToSpeech.speak(charSequence, TextToSpeech.QUEUE_FLUSH, params, DateUtils.getTimeStamp().toString())
                textToSpeech.setOnUtteranceProgressListener(TextToSpeechCustom())
                textToSpeech.Engine()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onInit(p0: Int) {
        if(p0 == TextToSpeech.SUCCESS){
            print("${this.javaClass.name}- onInit: success")
        }else{
            print("${this.javaClass.name}- onInit: failed")
        }
    }

    inner class TextToSpeechCustom: UtteranceProgressListener(){
        override fun onDone(p0: String?) {
            if(hasStartToSpeech){
                hasStartToSpeech = false
                activity?.runOnUiThread {
                    recognitionUtils.startToSpeech()
                }
            }
        }

        override fun onError(p0: String?) {
            print("${this.javaClass.name} - TextToSpeechCustom - onError:")
        }

        override fun onStart(p0: String?) {
            print("${this.javaClass.name} - TextToSpeechCustom - onStart:")
        }
    }
}