package br.com.shoppinglistapp.data.webservice.response

import br.com.shoppinglistapp.data.model.User
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("access_token") val token: String?,
    @SerializedName("user") val user: User?

)