package br.com.shoppinglistapp.view.presenter

import br.com.shoppinglistapp.view.mock.ShoppingListMock

class ShoppingListFragmentPresenter {
    fun getData(numberOfItems: Int = 20) = ShoppingListMock.getShoppingListData(numberOfItems)

}