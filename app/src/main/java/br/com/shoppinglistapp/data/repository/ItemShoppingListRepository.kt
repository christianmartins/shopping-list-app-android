package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils

class ItemShoppingListRepository {

    fun getItems(shoppingListId: String): List<ItemShoppingList>{
        return GlobalUtils.itemsShoppingList.filter { it.shoppingListId == shoppingListId }.sortedBy { it.selected }
    }

    fun deleteItem(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.remove(item)
    }

}