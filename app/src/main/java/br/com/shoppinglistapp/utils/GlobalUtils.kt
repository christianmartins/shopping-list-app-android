package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.App

object GlobalUtils {

    val db: AppDatabase by lazy {
        AppDatabase.getInstance(App.context!!)
    }

    var currentUser = ""

}