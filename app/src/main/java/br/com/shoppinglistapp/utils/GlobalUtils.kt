package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.App
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.extensions.nonNullable

object GlobalUtils {

    const val googleRecognitionServiceName = "com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"
    val shoppingLists = ArrayList<ShoppingList>()
    val itemsShoppingList = ArrayList<ItemShoppingList>()
    var fragmentAlive = ""
    val urlApp = App.context?.getString(R.string.url_app).nonNullable()

    fun clearLists(){
        shoppingLists.clear()
        itemsShoppingList.clear()
    }
}