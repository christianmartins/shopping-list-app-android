package br.com.shoppinglistapp.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.shoppinglistapp.data.model.BaseModel

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    abstract fun setItem(item: BaseModel)
}