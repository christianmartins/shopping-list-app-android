package br.com.shoppinglistapp.data.webservice

import br.com.shoppinglistapp.data.webservice.request.RequestLogin
import br.com.shoppinglistapp.data.webservice.request.RequestRegisterUser
import br.com.shoppinglistapp.data.webservice.request.RequestSaveShoppingList
import br.com.shoppinglistapp.data.webservice.response.ResponseLogin
import br.com.shoppinglistapp.data.webservice.response.ResponseSaveShoppingList
import br.com.shoppinglistapp.data.webservice.response.ResponseShoppingList
import br.com.shoppinglistapp.data.webservice.response.ResponseUserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShoppingListAppApi {

    @POST("auth/login")
    fun login(
        @Body args: RequestLogin
    ): Call<ResponseLogin>

    @POST("user/register")
    fun registerUser(
        @Body args: RequestRegisterUser
    ): Call<ResponseUserRegister>

    @POST("shopping-list/save")
    fun saveShoppingList(
        @Body args: RequestSaveShoppingList
    ): Call<ResponseSaveShoppingList>

    @GET("shopping-list/user")
    fun getShoppingListByUser(
        @Query("userId") userId: Int
    ): Call<ResponseShoppingList>

}