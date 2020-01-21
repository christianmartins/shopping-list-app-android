package br.com.shoppinglistapp.data.webservice.request

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.model.ShoppingList
import com.google.gson.annotations.SerializedName


data class RequestSaveItemShoppingList(
    @SerializedName("itemShoppingLists") val itemShoppingLists: List<ItemShoppingList>
)