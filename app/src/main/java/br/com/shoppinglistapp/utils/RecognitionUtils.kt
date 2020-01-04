package br.com.shoppinglistapp.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.R
import java.util.*

class RecognitionUtils {
    private var speechRecognizer: SpeechRecognizer? = null
    private val recognitionListenerUtils = RecognitionListener()

    init {
        speechRecognizer = getInstanceSpeechRecognizer()
        speechRecognizer?.setRecognitionListener(recognitionListenerUtils)
    }

    private fun getInstanceSpeechRecognizer(): SpeechRecognizer?{
        return App.context?.applicationContext?.let {
            SpeechRecognizer.createSpeechRecognizer(
                it,
                ComponentName.unflattenFromString(GlobalUtils.googleRecognitionServiceName)
            )
        }
    }

     private fun getSpeechIntent(context: Context): Intent{
         // for more: https://stackoverflow.com/questions/7973023/what-is-the-list-of-supported-languages-locales-on-android

//        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        speechIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 10000)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,  5000L)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000L)
//        speechIntent.putExtra("android.speech.extra.DICTATION_MODE", false)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)


         return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).let {
             it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
             it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
             it.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
             it.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
         }
     }


    fun startToSpeech(){
        App.context?.applicationContext?.let {
            if (SpeechRecognizer.isRecognitionAvailable(it)) {
                if(speechRecognizer == null){
                    speechRecognizer = getInstanceSpeechRecognizer()
                    speechRecognizer?.setRecognitionListener(recognitionListenerUtils)
                }
                speechRecognizer?.startListening(getSpeechIntent(it))
            } else {
                Handler(Looper.getMainLooper()).post{
                    Toast.makeText(it, it.getString(R.string.could_not_start_speech_recognizer), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}