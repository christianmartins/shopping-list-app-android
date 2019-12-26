package br.com.shoppinglistapp.utils

import android.content.ComponentName
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import br.com.shoppinglistapp.App
import java.util.*

class RecognitionUtils {

    fun startToSpeech(){
        // for more: https://stackoverflow.com/questions/7973023/what-is-the-list-of-supported-languages-locales-on-android

//        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context!!.packageName)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        speechIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 10000)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,  5000L)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000L)
//        speechIntent.putExtra("android.speech.extra.DICTATION_MODE", false)
//        speechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)


        // Use a language model based on free-form speech recognition.
        App.context?.applicationContext?.let {
            val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            speechIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
            speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, it.packageName)

            if (SpeechRecognizer.isRecognitionAvailable(it)) {
                val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(it, ComponentName.unflattenFromString(GlobalUtils.googleRecognitionServiceName))
                val recognitionListenerUtils = RecognitionListener()
                speechRecognizer.setRecognitionListener(recognitionListenerUtils)
                speechRecognizer.startListening(speechIntent)
            } else {
                Toast.makeText(it, "Erro ao iniciar speech to text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}