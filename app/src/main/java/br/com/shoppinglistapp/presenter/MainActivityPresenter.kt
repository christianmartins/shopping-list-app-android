package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ItemShoppingListMock
import br.com.shoppinglistapp.data.mock.ShoppingListMock

class MainActivityPresenter {
    fun getShoppingListData(numberOfItems: Int = 5) = ShoppingListMock.getShoppingListData(numberOfItems)
    fun getItemsShoppingListData(numberOfItems: Int = 5) = ItemShoppingListMock.getItemShoppingListData(numberOfItems)
}