package br.com.shoppinglistapp.view.activity

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    fun hideActionBar(){
        supportActionBar?.hide()
    }
}