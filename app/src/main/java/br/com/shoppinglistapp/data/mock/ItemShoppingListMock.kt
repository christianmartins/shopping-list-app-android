package br.com.shoppinglistapp.data.mock

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.DateUtils
import kotlin.collections.ArrayList

object ItemShoppingListMock {

    fun getItemShoppingListData(numberOfItems: Int): List<ItemShoppingList>{
        val mockList = ArrayList<ItemShoppingList>()
        (0 until numberOfItems).forEach { i ->
            ItemShoppingList(
                id = DateUtils.getTimeStamp().toString(),
                description = "Description $i",
                shoppingListId = "1",
                selected = false,
                deleted = false,
                createAt = DateUtils.getDateTime(),
                updateAt = DateUtils.getDateTime()
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}