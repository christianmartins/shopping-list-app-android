package br.com.shoppinglistapp.view.viewholder

import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.extensions.changeColor
import br.com.shoppinglistapp.extensions.setPaintFlagsStrikeThroughEffect
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
            setStrikeThroughEffect(item)
        }
    }

    private fun onClickDeleteItem(itemShoppingList: ItemShoppingList){
        deleted?.setOnClickListener {
            itemShoppingListListeners.deleteItem(itemShoppingList)
        }
    }

    private fun setStrikeThroughEffect(itemShoppingList: ItemShoppingList){
        description.setPaintFlagsStrikeThroughEffect(itemShoppingList.selected)
        description.changeColor(if(itemShoppingList.selected)android.R.color.darker_gray else android.R.color.black)
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