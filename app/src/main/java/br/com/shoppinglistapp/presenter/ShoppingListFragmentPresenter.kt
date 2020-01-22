package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ShoppingListMock
import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.data.repository.ShoppingListRepository

class ShoppingListFragmentPresenter {

    private val shoppingListRepository = ShoppingListRepository()

    fun createShoppingList(
        title: String,
        description: String = "",
        currentItemsToComplete: Int = 0,
        totalItemsToComplete: Int = 0,
        deleted: Boolean = false,
        sent: Boolean = false
    ) = ShoppingListMock.createShoppingList(title, description, currentItemsToComplete, totalItemsToComplete, deleted, sent)

    suspend fun sendShoppingList(){
        return shoppingListRepository.sendAndRefreshShoppingList()
    }

    suspend fun loadListByUser(){
        return shoppingListRepository.loadListByUser()
    }

    fun deleteItem(shoppingList: ShoppingList){
        return shoppingListRepository.markToDeleteItem(shoppingList)
    }

    fun getOrderedItems(): List<ShoppingList>{
        return shoppingListRepository.getOrderedItems()
    }
}