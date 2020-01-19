package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.repository.UserRepository

class LoginPresenter {

    suspend fun loginAsync(email: String, password: String): Boolean{
        return UserRepository().loginAsync(email, password)
    }
}