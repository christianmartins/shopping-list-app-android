package br.com.shoppinglistapp.view.model

data class ShoppingList (
    var id: String,
    var title: String,
    var description: String,
    var userId: String,
    var createAt: Long,
    var updateAt: Long,
    var deleted: Boolean
): BaseModel()