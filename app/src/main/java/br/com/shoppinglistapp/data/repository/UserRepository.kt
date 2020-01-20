package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.data.webservice.request.RequestRegisterUser
import br.com.shoppinglistapp.extensions.nonNullable
import br.com.shoppinglistapp.utils.GlobalUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
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

    suspend fun registerUser(email: String, password: String, firstName: String, lastName: String): Boolean{
        return try{
            val response = withContext(Dispatchers.IO){
                networkServiceProvider.getService().registerUser(RequestRegisterUser(email, password, firstName, lastName)).execute()
            }

            if(response.isSuccessful){
                response.body()?.success?:false
            }else{
                false
            }
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

}