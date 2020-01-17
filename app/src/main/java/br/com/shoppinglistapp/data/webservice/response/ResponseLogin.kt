package br.com.shoppinglistapp.data.webservice.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("access_token") val token: String?
)