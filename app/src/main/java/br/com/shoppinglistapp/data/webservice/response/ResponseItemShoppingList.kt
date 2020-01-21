package br.com.shoppinglistapp.data.webservice.response

import br.com.shoppinglistapp.data.model.ItemShoppingList
import com.google.gson.annotations.SerializedName

data class  ResponseItemShoppingList(
    @SerializedName("itemShoppingLists") val itemsShoppingList: List<ItemShoppingList>
)