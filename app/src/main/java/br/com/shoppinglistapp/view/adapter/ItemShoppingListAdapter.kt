package br.com.shoppinglistapp.view.adapter

import android.view.View
import android.view.ViewGroup
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import br.com.shoppinglistapp.view.viewholder.BaseViewHolder
import br.com.shoppinglistapp.view.viewholder.ItemShoppingListViewHolder

class ItemShoppingListAdapter(
    private val itemShoppingListListeners: ItemShoppingListListeners
): BaseAdapter() {

    private val itemsShoppingList = ArrayList<ItemShoppingList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemShoppingListViewHolder(
            View.inflate(parent.context, R.layout.item_shopping_list_view_holder_layout, null),
            itemShoppingListListeners
        )
    }

    override fun getItemId(position: Int): Long {
        return itemsShoppingList[position].id.hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return itemsShoppingList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = itemsShoppingList[position]
        holder.setItem(item)
    }

    fun setList(list: List<ItemShoppingList>){
        this.itemsShoppingList.addAll(list)
    }

    fun add(item: ItemShoppingList){
        this.itemsShoppingList.add(item)
        this.notifyDataSetChanged()
    }

    fun addAll(items: List<ItemShoppingList>){
        this.itemsShoppingList.addAll(items)
        this.notifyDataSetChanged()
    }

    fun remove(item: ItemShoppingList){
        this.itemsShoppingList.remove(item)
        this.notifyDataSetChanged()
    }

}