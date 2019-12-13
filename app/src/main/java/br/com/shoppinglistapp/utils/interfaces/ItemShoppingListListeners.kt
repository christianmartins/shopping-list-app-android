package br.com.shoppinglistapp.utils.interfaces

import br.com.shoppinglistapp.model.ItemShoppingList

interface ItemShoppingListListeners {
    fun deleteItem(item: ItemShoppingList)
}