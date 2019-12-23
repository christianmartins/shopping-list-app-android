package br.com.shoppinglistapp

import android.app.Application
import android.content.Context
import br.com.shoppinglistapp.utils.GlobalUtils
import com.facebook.stetho.Stetho

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        var context: Context? = null
    }
}