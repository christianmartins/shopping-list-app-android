package br.com.shoppinglistapp.data.webservice.response

import com.google.gson.annotations.SerializedName

data class ResponseSaveShoppingList(
    @SerializedName("success") val success: Boolean?
)
