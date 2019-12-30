package br.com.shoppinglistapp.utils

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.widget.Toast
import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import org.greenrobot.eventbus.EventBus


class RecognitionListener(private val params: ParamsCustom? = null): RecognitionListener {

    private val tag = this.javaClass.name

    override fun onReadyForSpeech(params: Bundle?) {
        println("$tag onReadyForSpeech")
    }

    override fun onBeginningOfSpeech() {
        println("$tag onBeginningOfSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {}

    override fun onBufferReceived(buffer: ByteArray?) {
        println("$tag onBufferReceived")
    }

    override fun onEndOfSpeech() {
        println("$tag onEndofSpeech")
    }

    override fun onError(error: Int) {
        App.context?.let {
            Toast.makeText(it,
                when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                    SpeechRecognizer.ERROR_CLIENT -> "Client side error"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                    SpeechRecognizer.ERROR_NO_MATCH -> "No match"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
                    SpeechRecognizer.ERROR_SERVER -> "error from server"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                    else -> "Didn't understand, please try again."
                }
                , Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResults(results: Bundle) {
        results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {data ->
            if(data.size > 0){
                val bestResult = data[0].toString().trim()
                EventBus.getDefault().post(RecognitionOnResultEvent(bestResult, params))
                println("$tag onResults $bestResult")
            }
        }
    }

    override fun onPartialResults(partialResults: Bundle) {
        partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let { data ->
            println("$tag onPartialResults " + data[0].toString())
        }
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        println("$tag onEvent $eventType")
    }
}