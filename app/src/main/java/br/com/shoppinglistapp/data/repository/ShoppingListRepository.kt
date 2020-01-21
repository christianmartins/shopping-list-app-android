package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.data.webservice.NetworkServiceProvider
import br.com.shoppinglistapp.data.webservice.request.RequestSaveShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.LoggedUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingListRepository {

    private val networkServiceProvider = NetworkServiceProvider()

    private fun findShoppingList(currentShoppingListId: String): ShoppingList?{
        return GlobalUtils.shoppingLists.find { it.id == currentShoppingListId }
    }

    fun updateShoppingListTotalItems(shoppingListId: String, newTotal: Int){
        findShoppingList(shoppingListId)?.let {
            it.totalItemsToComplete = newTotal
        }
    }

    suspend fun sendAndRefreshShoppingList(){
        if(LoggedUser.isLogged){
            val listToSend = GlobalUtils.shoppingLists.filter { it.sent.not() }
            val isSuccess = send(listToSend)
            if(isSuccess){ listToSend.onEach { it.sent = true } }
        }
    }

    private suspend fun send(shoppingLists: List<ShoppingList>): Boolean{
        val listToSend = RequestSaveShoppingList(shoppingLists)
        return try{
            val response = withContext(Dispatchers.IO){
                networkServiceProvider.getService().saveShoppingList(listToSend).execute()
            }
            response.body()?.success?:false
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }
}