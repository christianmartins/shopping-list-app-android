package br.com.shoppinglistapp.data.webservice

import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.BuildConfig
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.nonNullable
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider {

    fun getService(): ShoppingListAppApi {
        val url = App.context?.getString(if (BuildConfig.DEBUG) R.string.url_debug else R.string.url_release).nonNullable()
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient().newBuilder().build())
            .build()

        return retrofit.create(ShoppingListAppApi::class.java)
    }
}
