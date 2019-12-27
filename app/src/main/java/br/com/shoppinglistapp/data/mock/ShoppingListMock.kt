package br.com.shoppinglistapp.data.mock

import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.utils.DateUtils

object ShoppingListMock {

    fun getShoppingListData(numberOfItems: Int): List<ShoppingList>{
        val mockList = ArrayList<ShoppingList>()
        (0 until numberOfItems).forEach { i ->
            ShoppingList(
                id = "anonimo1${DateUtils.getTimeStamp()}",
                title = "Title $i",
                description = "Description $i",
                userId = "anonimo1",
                createAt = DateUtils.getDateTime(),
                updateAt = DateUtils.getDateTime(),
                authorName = "Anônimo",
                currentItemsToComplete = 0,
                totalItemsToComplete = 0,
                deleted = false
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}