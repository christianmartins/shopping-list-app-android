package br.com.shoppinglistapp.presenter

import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.extensions.nonNullable
import br.com.shoppinglistapp.utils.GlobalUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoginPresenter {
    private val networkServiceProvider = NetworkServiceProvider()

    fun loginAsync(userName: String, password: String): Deferred<Boolean>{
        return ProcessLifecycleOwner.get().lifecycleScope.async(Dispatchers.IO){
            try{
                val response = networkServiceProvider.getService().login(RequestLogin(userName, password)).execute()

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
}