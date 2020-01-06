package br.com.shoppinglistapp.view.viewholder

import android.view.View
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.BaseModel
import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.utils.DateUtils
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import com.google.android.material.textview.MaterialTextView

class ShoppingListViewHolder(
    itemView: View,
    private val clickHandler: ShoppingFragmentListClickHandler
): BaseViewHolder(itemView) {
    private val title = itemView.findViewById<MaterialTextView>(R.id.shopping_list_title)
    private val description = itemView.findViewById<MaterialTextView>(R.id.shopping_list_description)
    private val authorName = itemView.findViewById<MaterialTextView>(R.id.shopping_list_author_name)
    private val date = itemView.findViewById<MaterialTextView>(R.id.shopping_list_date)
    private val progress = itemView.findViewById<MaterialTextView>(R.id.shopping_list_items_for_concluision)

    private val context by lazy {
        itemView.context
    }

    override fun setItem(item: BaseModel) {
        if(item is ShoppingList){
            title?.text = item.title
            description?.text = item.description
            authorName?.text = context.getString(R.string.created_by, item.authorName)
            date?.text = DateUtils.getFormatDateTime(item.createAt)
            progress?.text = context.getString(R.string.shopping_list_current_progress, item.currentItemsToComplete, item.totalItemsToComplete)

            itemView.setOnClickListener {
                clickHandler.onClickItemList(item.id)
            }
        }
    }

}