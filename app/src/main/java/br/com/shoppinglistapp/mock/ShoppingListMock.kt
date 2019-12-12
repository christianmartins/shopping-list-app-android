package br.com.shoppinglistapp.mock

import br.com.shoppinglistapp.model.ShoppingList
import br.com.shoppinglistapp.utils.DateUtils

object ShoppingListMock {

    fun getShoppingListData(numberOfItems: Int): List<ShoppingList>{
        val mockList = ArrayList<ShoppingList>()
        (0 until numberOfItems).forEach { i ->
            ShoppingList(
                id = i.toString(),
                title = "title $i",
                description = "description $i",
                userId = "userId $i",
                createAt = DateUtils.getDateTime(),
                updateAt = DateUtils.getDateTime(),
                authorName = "authorName %i",
                currentItemsToComplete = i,
                totalItemsToComplete = i * numberOfItems,
                deleted = false
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}