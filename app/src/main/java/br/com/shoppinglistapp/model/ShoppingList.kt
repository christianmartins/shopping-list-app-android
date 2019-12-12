package br.com.shoppinglistapp.model

data class ShoppingList (
    var id: String,
    var title: String,
    var description: String,
    var userId: String,
    var authorName: String,
    var createAt: Long,
    var updateAt: Long,
    var deleted: Boolean,
    var totalItemsToComplete: Int,
    var currentItemsToComplete: Int
): BaseModel()