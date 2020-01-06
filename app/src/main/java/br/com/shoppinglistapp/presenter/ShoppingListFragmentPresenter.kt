package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.mock.ShoppingListMock

class ShoppingListFragmentPresenter {
    fun getData(numberOfItems: Int = 1) = getDataList(numberOfItems)[0]

    fun getDataList(numberOfItems: Int = 5) = ShoppingListMock.getShoppingListData(numberOfItems)

}