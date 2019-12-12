package br.com.shoppinglistapp.view.viewholder

import android.view.View
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.model.ShoppingList
import br.com.shoppinglistapp.utils.DateUtils
import com.google.android.material.textview.MaterialTextView

class ShoppingListViewHolder(itemView: View): BaseViewHolder(itemView) {
    private val title = itemView.findViewById<MaterialTextView>(R.id.shopping_list_title)
    private val description = itemView.findViewById<MaterialTextView>(R.id.shopping_list_description)
    private val authorName = itemView.findViewById<MaterialTextView>(R.id.shopping_list_author_name)
    private val date = itemView.findViewById<MaterialTextView>(R.id.shopping_list_date)
    private val progress = itemView.findViewById<MaterialTextView>(R.id.shopping_list_items_for_concluision)

    private val context by lazy {
        itemView.context
    }

    override fun setItem(item: Any) {
        if(item is ShoppingList){
            title?.text = item.title
            description?.text = item.description
            authorName?.text = item.authorName
            date?.text = DateUtils.getFormatDateTime(item.createAt)
            progress?.text = context.getString(R.string.shopping_list_current_progress, item.currentItemsToComplete, item.totalItemsToComplete)
        }
    }
}