package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.extensions.nonNullable
import br.com.shoppinglistapp.utils.GlobalUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginPresenter {
    private val networkServiceProvider = NetworkServiceProvider()

    suspend fun loginAsync(email: String, password: String): Boolean{
        return try{
            val response = withContext(Dispatchers.IO){
                networkServiceProvider.getService().login(RequestLogin(email, password)).execute()
            }

            if(response.isSuccessful){
                val token = response.body()?.token
                GlobalUtils.token = token.nonNullable()
                token != null
            }else{
                false
            }
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}