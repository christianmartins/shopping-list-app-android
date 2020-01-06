package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ItemShoppingListMock
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.repository.ItemShoppingListRepository

import br.com.shoppinglistapp.data.repository.ShoppingListRepository

class ItemShoppingListFragmentPresenter{

    private val shoppingListRepository = ShoppingListRepository()
    private val itemShoppingListRepository = ItemShoppingListRepository()

    private fun getDataList(numberOfItems: Int = 200, shoppingListId: String) = ItemShoppingListMock.getItemShoppingListData(numberOfItems, shoppingListId)

    fun getData(numberOfItems: Int = 1, shoppingListId: String) = getDataList(numberOfItems, shoppingListId)[0]

    fun getItems(shoppingListId: String): List<ItemShoppingList>{
        return itemShoppingListRepository.getItems(shoppingListId)
    }

    private fun listForTest(shoppingListId: String) = getDataList(shoppingListId = shoppingListId).distinctBy { it.id }.let {
        it.forEachIndexed { index, itemShoppingList ->
            if(index > (it.size / 2)){
                itemShoppingList.selected = true
            }
        }
        it
    }

    fun deleteItem(item: ItemShoppingList) = itemShoppingListRepository.deleteItem(item)

    fun updateShoppingListTotalItems(shoppingListId: String, newTotal: Int){
        shoppingListRepository.updateShoppingListTotalItems(shoppingListId, newTotal)
    }

}