package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.model.ItemShoppingList
import br.com.shoppinglistapp.presenter.ItemShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import br.com.shoppinglistapp.view.adapter.ItemShoppingListAdapter
import kotlinx.android.synthetic.main.item_shopping_list_layout.*

class ItemShoppingListFragment: BaseCollectionFragment(), ItemShoppingListListeners {

    private val presenter by lazy {
        ItemShoppingListFragmentPresenter()
    }

    private val adapter by lazy {
        ItemShoppingListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getFab()?.setImageResource(R.drawable.ic_mic_black_24dp)
        return inflater.inflate(R.layout.item_shopping_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        onClickAddItem()
    }

    private fun initList(){
        val list = presenter.getData()
        adapter.setList(list)
        item_shopping_list_recycler_view?.adapter = adapter
    }

    override fun deleteItem(item: ItemShoppingList) {
        adapter.remove(item)
    }

    private fun onClickAddItem(){
        getFab()?.setOnClickListener {
            val item = presenter.getData(1)
            adapter.add(item[0])
        }

    }

}