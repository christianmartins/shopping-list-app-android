package br.com.shoppinglistapp.data.webservice.request

import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("username") val email: String,
    @SerializedName("password") val password: String
)