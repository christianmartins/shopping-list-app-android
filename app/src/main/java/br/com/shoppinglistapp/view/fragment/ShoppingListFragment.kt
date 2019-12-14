package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.presenter.ShoppingListFragmentPresenter
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main.shopping_list_layout.*

class ShoppingListFragment: BaseCollectionFragment() {

    private val presenter by lazy { ShoppingListFragmentPresenter() }

    private val adapter by lazy { ShoppingListAdapter() }

    private val shoppingLists by lazy {  presenter.getData() }

    init {
        adapter.setList(shoppingLists)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getFab()?.setImageResource(R.drawable.ic_add_white_24dp)
        return inflater.inflate(R.layout.shopping_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        onClickFloatingButton()
    }

    private fun initAdapter(){
        shopping_list_recycler_view?.adapter = adapter
    }

    private fun onClickFloatingButton(){
        getFab()?.setOnClickListener {
            findNavController().navigate(
                ShoppingListFragmentDirections.actionShoppingListFragmentToItemShoppingListFragment()
            )
        }
    }


}