package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.view.presenter.ShoppingListFragmentPresenter

class ShoppingListFragment: BaseCollection() {

    private val presenter by lazy {
        ShoppingListFragmentPresenter()
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