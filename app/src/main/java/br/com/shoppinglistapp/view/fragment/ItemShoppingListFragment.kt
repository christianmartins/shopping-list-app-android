package br.com.shoppinglistapp.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.extensions.setEmptyList
import br.com.shoppinglistapp.presenter.ItemShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import br.com.shoppinglistapp.view.adapter.ItemShoppingListAdapter
import kotlinx.android.synthetic.main._empty_list_layout.*
import kotlinx.android.synthetic.main.item_shopping_list_layout.*

class ItemShoppingListFragment: BaseCollectionFragment(), ItemShoppingListListeners {

    private val presenter by lazy {
        ItemShoppingListFragmentPresenter()
    }

    private val adapter by lazy {
        ItemShoppingListAdapter(this)
    }

    private val currentShoppingListId by lazy {
        arguments?.getString("shoppingListId")?: ""
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
        item_shopping_list_recycler_view?.adapter = adapter
        loadList()
    }

    private fun loadList() {
        val filteredList = GlobalUtils.itemsShoppingList.filter { it.shoppingListId == currentShoppingListId }
        adapter.addAll(filteredList)
        empty_list.text = getString(R.string.item_shopping_list_empty_list)
        empty_list.setEmptyList(adapter.itemCount)
    }

    override fun deleteItem(item: ItemShoppingList) {
        GlobalUtils.itemsShoppingList.remove(item)
        adapter.remove(item)
        updateTotalItemsToCompleteShoppingList()
    }

    private fun onClickAddItem(){
        getFab()?.setOnClickListener {
            RecognitionUtils().startToSpeech()
        }
    }

    private fun updateTotalItemsToCompleteShoppingList(){
        GlobalUtils.shoppingLists.find { it.id == currentShoppingListId }?.let {
            it.totalItemsToComplete = adapter.itemCount
        }
        empty_list.setEmptyList(adapter.itemCount)
    }

    override fun onMessageEvent(event: MessageEvent) {
        super.onMessageEvent(event)
        when(event){
            is RecognitionOnResultEvent -> {
                if(GlobalUtils.fragmentAlive == this.javaClass.name){
                    val results = event.bestResult.split(" e ", ignoreCase = true)
                    results.forEach {
                        val itemShoppingList = presenter.getData().copy(
                            description = it,
                            shoppingListId = currentShoppingListId
                        )
                        GlobalUtils.itemsShoppingList.add(itemShoppingList)
                        adapter.add(itemShoppingList)
                    }
                    updateTotalItemsToCompleteShoppingList()
                }
            }
        }
    }
}