package br.com.shoppinglistapp.view.viewholder

import android.view.View
import android.widget.ImageView
import androidx.annotation.StringRes
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.BaseModel
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.extensions.changeColor
import br.com.shoppinglistapp.extensions.setPaintFlagsStrikeThroughEffect
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import com.google.android.material.textview.MaterialTextView


class ItemShoppingListViewHolder(
    itemView: View,
    private val itemShoppingListListeners: ItemShoppingListListeners
): BaseViewHolder(itemView) {
    private val description = itemView.findViewById<MaterialTextView>(R.id.item_shopping_list_description)
    private val deleted = itemView.findViewById<ImageView >(R.id.item_shopping_list_delete)
    private val imgCheck = itemView.findViewById<ImageView >(R.id.item_shopping_list_img_check)

    override fun setItem(item: BaseModel) {
        if(item is ItemShoppingList){
            description?.text = item.description
            onClickDeleteItem(item)
            setStrikeThroughEffect(item)
            setImgCheckVisibility(item.selected)
            onSelectedItem(item)
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

    private fun onSelectedItem(itemShoppingList: ItemShoppingList){
        itemView.setOnClickListener {
            itemShoppingList.selected = !itemShoppingList.selected
            setStrikeThroughEffect(itemShoppingList)
            setImgCheckVisibility(itemShoppingList.selected)
            itemShoppingListListeners.onSelectedItem(itemShoppingList)
        }
    }

    private fun setImgCheckVisibility(isVisible: Boolean){
        imgCheck.visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
    }
}