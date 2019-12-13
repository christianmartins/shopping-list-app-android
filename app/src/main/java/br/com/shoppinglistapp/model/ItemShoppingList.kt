package br.com.shoppinglistapp.model

data class ItemShoppingList(
    var id: String,
    var description: String,
    var selected: Boolean,
    var shoppingListId: String,
    var deleted: Boolean,
    var createAt: Long,
    var updateAt: Long
) : BaseModel()