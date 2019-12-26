package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ShoppingListMock

class ShoppingListFragmentPresenter {
    fun getData(numberOfItems: Int = 1) = ShoppingListMock.getShoppingListData(numberOfItems)[0]
}