package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.model.ShoppingList

object GlobalUtils {

    val db: AppDatabase by lazy {
        AppDatabase.getInstance(App.context!!)
    }

    var currentUser = ""

    val shoppingLists = ArrayList<ShoppingList>()
    val itemsShoppingList = ArrayList<ItemShoppingList>()
}