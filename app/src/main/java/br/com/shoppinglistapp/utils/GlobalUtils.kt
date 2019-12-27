package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.model.ShoppingList

object GlobalUtils {

    const val googleRecognitionServiceName = "com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"

    val db: AppDatabase by lazy {
        AppDatabase.getInstance(App.context!!)
    }

    val shoppingLists = ArrayList<ShoppingList>()
    val itemsShoppingList = ArrayList<ItemShoppingList>()
    var currentUser = ""
    var fragmentAlive = ""
}