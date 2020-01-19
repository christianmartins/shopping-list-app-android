package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.data.webservice.request.RequestUserRegister
import br.com.shoppinglistapp.data.webservice.response.Message
import br.com.shoppinglistapp.data.webservice.response.ResponseUserRegister
import br.com.shoppinglistapp.extensions.nonNullable
import br.com.shoppinglistapp.utils.GlobalUtils
import com.google.gson.Gson
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

    suspend fun userRegister(email: String, password: String, firstName: String, lastName: String): ResponseUserRegister?{
        return try{
            val response = withContext(Dispatchers.IO){
                networkServiceProvider.getService().userRegister(RequestUserRegister(email, password, firstName, lastName)).execute()
            }

            if(response.isSuccessful){
                response.body()
            }else{
                val errorBody = response.errorBody()
                println(errorBody?.string())
                Gson().toJson(errorBody?.string())
                ResponseUserRegister(false, null, null, Message(1, "", "Usuário já cadastrado"))
            }
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

}