package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ItemShoppingListMock

class ItemShoppingListFragmentPresenter {
    fun getData(numberOfItems: Int = 1) = ItemShoppingListMock.getItemShoppingListData(numberOfItems)[0]
}