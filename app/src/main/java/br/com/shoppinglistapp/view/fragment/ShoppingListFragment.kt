package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import br.com.shoppinglistapp.view.presenter.ShoppingListFragmentPresenter
import kotlinx.android.synthetic.main.shopping_list_layout.*

class ShoppingListFragment: BaseCollection() {

    private val presenter by lazy {
        ShoppingListFragmentPresenter()
    }

    private val adapter by lazy {
        ShoppingListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shopping_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = presenter.getData()
        adapter.setList(list)
        shopping_list_recycler_view?.adapter = adapter
    }

    override fun initAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initRecycler() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}