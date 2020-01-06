package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils

class ShoppingListRepository {

    private fun findShoppingList(currentShoppingListId: String): ShoppingList?{
        return GlobalUtils.shoppingLists.find { it.id == currentShoppingListId }
    }

    fun updateShoppingListTotalItems(shoppingListId: String, newTotal: Int){
        findShoppingList(shoppingListId)?.let {
            it.totalItemsToComplete = newTotal
        }
    }

}