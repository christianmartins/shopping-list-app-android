package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.mock.ItemShoppingListMock
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils

class ItemShoppingListRepository {

    fun add(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.add(item)
    }

    fun deleteItem(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.remove(item)
    }

    private fun getDataList(numberOfItems: Int = 200, shoppingListId: String) = ItemShoppingListMock.getItemShoppingListData(numberOfItems, shoppingListId)

    fun getOrderedItems(shoppingListId: String): List<ItemShoppingList>{
        return orderingList(getItems(shoppingListId))
    }

    private fun orderingList(list: List<ItemShoppingList>): List<ItemShoppingList> {
        return list.sortedWith(
            compareBy(
                { it.selected }, { it.createAt }
            )
        )
    }

    fun updateSelectedItem(selectedItem: ItemShoppingList, shoppingListId: String) {
        getItems(shoppingListId).let {list ->
            list.find { it.id == selectedItem.id }?.selected = selectedItem.selected
        }
    }

    private fun getItems(shoppingListId: String): List<ItemShoppingList>{
        return GlobalUtils.itemsShoppingList.filter { it.shoppingListId == shoppingListId }
    }

    fun listForTest(shoppingListId: String) = getDataList(shoppingListId = shoppingListId).distinctBy { it.id }.let {
        it.forEachIndexed { index, itemShoppingList ->
            if(index > (it.size / 2)){
                itemShoppingList.selected = true
            }
        }
        it
    }
}