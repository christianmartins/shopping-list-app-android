package br.com.shoppinglistapp.data.webservice.request

import br.com.shoppinglistapp.data.model.BaseModel
import com.google.gson.annotations.SerializedName

data class RequestUserRegister(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String
): BaseModel()