package br.com.shoppinglistapp.data.webservice.response

import com.google.gson.annotations.SerializedName

data class ResponseSaveItemShoppingList(
    @SerializedName("success") val success: Boolean?
)
