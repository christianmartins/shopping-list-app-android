package br.com.shoppinglistapp.data.mock

import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.utils.DateUtils

object ShoppingListMock {

    fun getShoppingListData(numberOfItems: Int): List<ShoppingList>{
        val mockList = ArrayList<ShoppingList>()
        (0 until numberOfItems).forEach { i ->
            ShoppingList(
                id = i.toString(),
                title = "Title $i",
                description = "Description $i",
                userId = "UserId $i",
                createAt = DateUtils.getDateTime(),
                updateAt = DateUtils.getDateTime(),
                authorName = "A uthorName $i",
                currentItemsToComplete = i,
                totalItemsToComplete = numberOfItems,
                deleted = false
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}