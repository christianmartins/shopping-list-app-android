package br.com.shoppinglistapp.view.viewholder

import android.view.View
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.model.ItemShoppingList
import br.com.shoppinglistapp.model.ShoppingList
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import com.google.android.material.textview.MaterialTextView

class ItemShoppingListViewHolder(
    itemView: View,
    private val itemShoppingListListeners: ItemShoppingListListeners
): BaseViewHolder(itemView) {
    private val description = itemView.findViewById<MaterialTextView>(R.id.item_shopping_list_description)
    private val deleted = itemView.findViewById<ImageView >(R.id.item_shopping_list_delete)

    override fun setItem(item: Any) {
        if(item is ItemShoppingList){
            description?.text = item.description
            onClickDeleteItem(item)
        }
    }

    private fun onClickDeleteItem(itemShoppingList: ItemShoppingList){
        deleted?.setOnClickListener {
            itemShoppingListListeners.deleteItem(itemShoppingList)
        }
    }
}