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
        setSection(holder, item)
        holder.setItem(item)
    }

    private fun setSection(baseViewHolder: BaseViewHolder, itemShoppingList: ItemShoppingList){
        val myHolder = baseViewHolder as ItemShoppingListViewHolder
        val isFirstNoSelected = itemsShoppingList.firstOrNull{ !it.selected }?.id == itemShoppingList.id

        try{
            if(isFirstNoSelected){
                myHolder.setSection(true, R.string.not_added_in_cart)
            }else{
                val isFirstSelected= itemsShoppingList.firstOrNull{ it.selected }?.id == itemShoppingList.id
                myHolder.setSection(isFirstSelected, R.string.added_in_cart)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    fun add(item: ItemShoppingList){
        this.itemsShoppingList.add(item)
        this.notifyItemInserted(this.itemsShoppingList.size)
    }

    fun addAll(items: List<ItemShoppingList>){
        this.itemsShoppingList.addAll(items)
        this.notifyDataSetChanged()
    }

    fun remove(item: ItemShoppingList){
        val position = this.itemsShoppingList.indexOf(item)
        this.itemsShoppingList.removeAt(position)
        this.notifyItemRemoved(position)
    }

    fun clear(){
        itemsShoppingList.clear()
    }

}