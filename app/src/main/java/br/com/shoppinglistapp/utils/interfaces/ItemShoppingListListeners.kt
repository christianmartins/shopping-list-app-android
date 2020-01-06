package br.com.shoppinglistapp.utils.interfaces

import br.com.shoppinglistapp.data.model.ItemShoppingList

interface ItemShoppingListListeners {
    fun deleteItem(item: ItemShoppingList)
    fun onSelectedItem(item: ItemShoppingList)
}