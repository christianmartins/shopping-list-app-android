package br.com.shoppinglistapp.data.webservice

import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.data.webservice.response.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ShoppingListAppApi {

    @POST("auth/login")
    fun login(
        @Body args: RequestLogin
    ): Call<ResponseLogin>

}