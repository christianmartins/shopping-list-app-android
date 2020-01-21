package br.com.shoppinglistapp.data.webservice.response

import br.com.shoppinglistapp.data.model.ShoppingList
import com.google.gson.annotations.SerializedName

data class  ResponseShoppingList(
    @SerializedName("shoppingLists") val shoppingLists: List<ShoppingList>
)