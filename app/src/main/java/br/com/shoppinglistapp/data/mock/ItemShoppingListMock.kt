package br.com.shoppinglistapp.data.mock

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.DateUtils
import br.com.shoppinglistapp.utils.GlobalUtils
import kotlin.collections.ArrayList

object ItemShoppingListMock {

    fun getItemShoppingListData(numberOfItems: Int, shoppingListId: String = ""): List<ItemShoppingList>{
        val mockList = ArrayList<ItemShoppingList>()
        (0 until numberOfItems).forEach { _ ->
            val dateTime = DateUtils.getDateTime()
            ItemShoppingList(
                id = shoppingListId + DateUtils.getTimeStamp().toString(),
                description = "Description",
                shoppingListId = shoppingListId,
                selected = false,
                deleted = false,
                createAt = dateTime,
                updateAt = dateTime
            ).apply {
                mockList.add(this)
            }
        }
        return mockList
    }
}