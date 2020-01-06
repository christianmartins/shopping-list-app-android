package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.model.ShoppingList

object GlobalUtils {

    const val googleRecognitionServiceName = "com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"
    val shoppingLists = ArrayList<ShoppingList>()
    val itemsShoppingList = ArrayList<ItemShoppingList>()
    var fragmentAlive = ""

}