package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.mock.ShoppingListMock

class ShoppingListFragmentPresenter {
    fun getData(numberOfItems: Int = 20) = ShoppingListMock.getShoppingListData(numberOfItems)
}