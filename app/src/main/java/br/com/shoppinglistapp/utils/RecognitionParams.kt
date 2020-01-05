package br.com.shoppinglistapp.utils

import br.com.shoppinglistapp.utils.enum.ActionType

data class RecognitionParams (
    var actionsType: ActionType = ActionType.NONE,
    var currentShoppingListId: String = ""
)