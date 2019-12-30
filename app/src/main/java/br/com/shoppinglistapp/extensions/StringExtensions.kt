package br.com.shoppinglistapp.extensions

fun String?.nonNullable(): String{
    return this?:""
}