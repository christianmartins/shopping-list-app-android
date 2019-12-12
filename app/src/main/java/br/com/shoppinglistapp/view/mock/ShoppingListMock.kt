package br.com.shoppinglistapp.view.mock

import br.com.shoppinglistapp.view.model.ShoppingList
import br.com.shoppinglistapp.view.utils.DateUtils

object ShoppingListMock {

    fun getShoppingListData(numberOfItems: Int): List<ShoppingList>{
        val mockList = ArrayList<ShoppingList>()
        (0 until numberOfItems).forEach { i ->
            ShoppingList(
                shoppingListId = i.toString(),
                title = "title $i",
                description = "title $i",
                userId = "title $i",
                createAt = DateUtils.getDateTime(),
                updateAt = DateUtils.getDateTime(),
                deleted = false
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}