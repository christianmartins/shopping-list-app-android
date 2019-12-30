package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.utils.enum.ActionType

data class ParamsCustom (
    var actionsType: ActionType,
    var currentShoppingListId: String
)