package br.com.shoppinglistapp.data.repository

import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.utils.GlobalUtils

class ShoppingListRepository {
    private val shoppingListDao = GlobalUtils.db.getShoppingListDao()

    suspend fun insertAsync(shoppingLists: List<ShoppingList>){
        shoppingListDao.insert(*shoppingLists.toTypedArray())
    }

    suspend fun getListAsync(userId: String = GlobalUtils.currentUser): List<ShoppingList>{
        return shoppingListDao.getList(userId)
    }
}