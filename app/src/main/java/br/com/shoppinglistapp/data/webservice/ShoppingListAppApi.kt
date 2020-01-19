package br.com.shoppinglistapp.data.webservice

import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.data.webservice.request.RequestUserRegister
import br.com.shoppinglistapp.data.webservice.response.ResponseLogin
import br.com.shoppinglistapp.data.webservice.response.ResponseUserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ShoppingListAppApi {

    @POST("auth/login")
    fun login(
        @Body args: RequestLogin
    ): Call<ResponseLogin>

    @POST("user/register")
    fun userRegister(
        @Body args: RequestUserRegister
    ): Call<ResponseUserRegister>

}