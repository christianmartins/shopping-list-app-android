package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ShoppingListMock
import br.com.shoppinglistapp.data.repository.ShoppingListRepository

class ShoppingListFragmentPresenter {

    fun createShoppingList(
        title: String,
        description: String = "",
        currentItemsToComplete: Int = 0,
        totalItemsToComplete: Int = 0,
        deleted: Boolean = false,
        sent: Boolean = false
    ) = ShoppingListMock.createShoppingList(title, description, currentItemsToComplete, totalItemsToComplete, deleted, sent)

    suspend fun sendShoppingList(){
        return ShoppingListRepository().sendAndRefreshShoppingList()
    }

}