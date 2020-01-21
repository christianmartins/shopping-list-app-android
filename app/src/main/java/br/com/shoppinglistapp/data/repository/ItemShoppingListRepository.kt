package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestSaveItemShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.LoggedUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemShoppingListRepository {
    private val networkServiceProvider = NetworkServiceProvider()

    fun add(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.add(item)
    }

    fun deleteItem(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.remove(item)
    }

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

    suspend fun loadItemsShoppingListByShoppingListId(shoppingListId: String){
        try{
            if(LoggedUser.isLogged){
                val initTime = System.nanoTime()
                val response = withContext(Dispatchers.IO){
                    networkServiceProvider.getService().getItemsShoppingListByShoppingListId(shoppingListId).execute()
                }
                response.body()?.itemsShoppingList?.let { itemsShoppingListNonNullable ->
                    val receivedItemsShoppingLists = itemsShoppingListNonNullable.onEach { it.sent = true }
                    val initAddInListTime = System.nanoTime()
                    filteredItemsShoppingList(receivedItemsShoppingLists, shoppingListId)
                    println("${this@ItemShoppingListRepository.javaClass.name} - addInList time: ${ (System.nanoTime() - initAddInListTime) / 1000000}")
                    println("${this@ItemShoppingListRepository.javaClass.name} - loadItemsShoppingList time: ${ (System.nanoTime() - initTime) / 1000000}")
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun filteredItemsShoppingList(receivedItemsShoppingLists: List<ItemShoppingList>, shoppingListId: String){
        val oldList = ArrayList(GlobalUtils.itemsShoppingList)
        val newList = ArrayList<ItemShoppingList>()

        newList.addAll(oldList.filter { it.shoppingListId != shoppingListId })
        newList.addAll(receivedItemsShoppingLists)

        GlobalUtils.itemsShoppingList.clear()
        GlobalUtils.itemsShoppingList.addAll(newList.sortedBy { it.shoppingListId })

    }

    suspend fun sendAndRefreshItemShoppingList(shoppingListId: String){
        if(LoggedUser.isLogged){
            val listToSend = GlobalUtils.itemsShoppingList.filter { it.sent.not() && it.shoppingListId == shoppingListId}
            if(listToSend.isNotEmpty()){
                val isSuccess = send(listToSend)
                if(isSuccess){ listToSend.onEach { it.sent = true } }
            }
        }
    }

    private suspend fun send(itemsShoppingList: List<ItemShoppingList>): Boolean{
        return try{
            val listToSend = RequestSaveItemShoppingList(itemsShoppingList)
            val response = withContext(Dispatchers.IO){
                networkServiceProvider.getService().saveItemsShoppingList(listToSend).execute()
            }
            response.body()?.success?:false
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}