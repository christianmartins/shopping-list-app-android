package br.com.shoppinglistapp.view.fragment

import androidx.recyclerview.widget.RecyclerView

abstract class BaseCollection: BaseFragment() {

//    protected lateinit var adapter: RecyclerView.Adapter<*>
    abstract fun initAdapter()
    abstract fun initRecycler()
    abstract fun loadList()
}