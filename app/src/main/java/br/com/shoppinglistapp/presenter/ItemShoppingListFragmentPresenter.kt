package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ItemShoppingListMock
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.repository.ItemShoppingListRepository

import br.com.shoppinglistapp.data.repository.ShoppingListRepository

class ItemShoppingListFragmentPresenter{

    private val shoppingListRepository = ShoppingListRepository()
    private val itemShoppingListRepository = ItemShoppingListRepository()

    fun add(item: ItemShoppingList) {
        itemShoppingListRepository.add(item)
    }

    private fun getDataList(numberOfItems: Int = 200, shoppingListId: String) = ItemShoppingListMock.getItemShoppingListData(numberOfItems, shoppingListId)

    fun getData(numberOfItems: Int = 1, shoppingListId: String) = getDataList(numberOfItems, shoppingListId)[0]

    fun getOrderedItems(shoppingListId: String): List<ItemShoppingList>{
        return itemShoppingListRepository.getOrderedItems(shoppingListId)
    }

    fun updateSelectedItem(selectedItem: ItemShoppingList, shoppingListId: String) {
        itemShoppingListRepository.updateSelectedItem(selectedItem, shoppingListId)
    }

    fun deleteItem(item: ItemShoppingList) = itemShoppingListRepository.deleteItem(item)

    fun updateShoppingListTotalItems(shoppingListId: String, newTotal: Int){
        shoppingListRepository.updateShoppingListTotalItems(shoppingListId, newTotal)
    }

    suspend fun loadItemsShoppingListByShoppingListId(shoppingListId: String){
        itemShoppingListRepository.loadItemsShoppingListByShoppingListId(shoppingListId)
    }

    suspend fun sendItemsShoppingList(shoppingListId: String){
        return itemShoppingListRepository.sendAndRefreshItemShoppingList(shoppingListId)
    }
}