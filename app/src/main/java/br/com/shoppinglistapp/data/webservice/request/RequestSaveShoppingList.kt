package br.com.shoppinglistapp.data.webservice.request

import br.com.shoppinglistapp.data.model.ShoppingList
import com.google.gson.annotations.SerializedName


data class RequestSaveShoppingList(
    @SerializedName("shoppingList") val shoppingList: List<ShoppingList>
)