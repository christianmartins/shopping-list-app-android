package br.com.shoppinglistapp.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import com.google.android.material.textview.MaterialTextView

class ItemShoppingListViewHolder(
    itemView: View,
    private val itemShoppingListListeners: ItemShoppingListListeners
): BaseViewHolder(itemView) {
    private val section = itemView.findViewById<MaterialTextView >(R.id.item_shopping_list_section)
    private val description = itemView.findViewById<MaterialTextView>(R.id.item_shopping_list_description)
    private val deleted = itemView.findViewById<ImageView >(R.id.item_shopping_list_delete)
    private val context = itemView.context

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

    fun setSection(hasVisible: Boolean, @StringRes stringRes: Int){
        section.visibility = if(hasVisible){
            section.text = context.getString(stringRes)
             View.VISIBLE
        }else{
            View.GONE
        }
    }
}