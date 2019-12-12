package br.com.shoppinglistapp.view.adapter

import android.view.View
import android.view.ViewGroup
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.model.ShoppingList
import br.com.shoppinglistapp.view.viewholder.BaseViewHolder
import br.com.shoppinglistapp.view.viewholder.ShoppingListViewHolder

class ShoppingListAdapter: BaseAdapter() {

    private val shoppingList = ArrayList<ShoppingList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ShoppingListViewHolder(
            View.inflate(parent.context, R.layout.shopping_list_view_holder_layout, null)
        )
    }

    override fun getItemId(position: Int): Long {
        return shoppingList[position].id.hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = shoppingList[position]
        holder.setItem(item)
    }

    fun setList(list: List<ShoppingList>){
        this.shoppingList.addAll(list)
    }
}