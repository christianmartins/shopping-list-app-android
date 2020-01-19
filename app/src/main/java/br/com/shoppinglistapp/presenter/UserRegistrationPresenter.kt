package br.com.shoppinglistapp.presenter

import br.com.shoppinglistapp.data.repository.UserRepository
import br.com.shoppinglistapp.data.webservice.response.ResponseUserRegister

class UserRegistrationPresenter{


    suspend fun userRegister(email: String, password: String, firstName: String, lastName: String): ResponseUserRegister? {
        return UserRepository().userRegister(email, password, firstName, lastName)
    }

    suspend fun loginAsync(email: String, password: String): Boolean{
        return UserRepository().loginAsync(email, password)
    }

}