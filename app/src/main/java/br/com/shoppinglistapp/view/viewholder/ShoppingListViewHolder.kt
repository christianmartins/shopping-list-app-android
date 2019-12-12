package br.com.shoppinglistapp.view.viewholder

import android.view.View
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.view.model.ShoppingList
import com.google.android.material.textview.MaterialTextView

class ShoppingListViewHolder(itemView: View): BaseViewHolder(itemView) {
    private val title = itemView.findViewById<MaterialTextView>(R.id.shopping_list_title)
    private val description = itemView.findViewById<MaterialTextView>(R.id.shopping_list_description)

    override fun setItem(item: Any) {
        if(item is ShoppingList){
            title?.text = item.title
            description?.text = item.description
        }
    }
}