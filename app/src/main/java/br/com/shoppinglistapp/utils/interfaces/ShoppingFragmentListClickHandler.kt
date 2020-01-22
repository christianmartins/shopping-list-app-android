package br.com.shoppinglistapp.utils.interfaces

import br.com.shoppinglistapp.data.model.ShoppingList

interface ShoppingFragmentListClickHandler {
    fun onClickItemList(shoppingListId: String)
    fun onClickDeleteItemList(shoppingList: ShoppingList)
    fun onClickEditItemList(shoppingList: ShoppingList)
}
