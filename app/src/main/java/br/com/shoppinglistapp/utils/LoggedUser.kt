package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.data.model.User
import br.com.shoppinglistapp.extensions.nonNullable

object LoggedUser {

    var user: User? = null
    var isLogged: Boolean = false
    var token = ""

    fun getUserId(): String{
        return user?.id?: "anonymous"
    }

    fun getAuthorName(): String{
        return user?.let {
            "${it.firstName} ${it.lastName}"
        }?: "anonymous"
    }

    fun isLogged(token: String, user: User){
        this.token = token
        this.user = user
        this.isLogged = true
    }

    fun clear(){
        token = ""
        user = null
        isLogged = false
    }
}