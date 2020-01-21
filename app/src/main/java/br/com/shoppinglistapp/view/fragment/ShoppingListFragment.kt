package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ShoppingList
import br.com.shoppinglistapp.extensions.setEmptyList
import br.com.shoppinglistapp.extensions.yesAnswer
import br.com.shoppinglistapp.presenter.ShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.LoggedUser
import br.com.shoppinglistapp.utils.RecognitionParams
import br.com.shoppinglistapp.utils.enum.ActionType
import br.com.shoppinglistapp.utils.event.RecognitionOnErrorEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main._empty_list_layout.*
import kotlinx.android.synthetic.main.shopping_list_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ShoppingListFragment: BaseCollectionFragment(), ShoppingFragmentListClickHandler{

    private val presenter by lazy { ShoppingListFragmentPresenter() }

    private val adapter by lazy { ShoppingListAdapter(this) }

    private var jobRefresh: Job? = null

    private fun isRefreshing(isRefresh: Boolean){
        activity?.runOnUiThread {shopping_list_swipe_refresh.isRefreshing = isRefresh}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getFab()?.setImageResource(R.drawable.ic_add_white_24dp)
        return inflater.inflate(R.layout.shopping_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initDataShoppingList()
        onRefreshListener()
    }

    private fun initDataShoppingList(){
        if(LoggedUser.isLogged)
            lifecycleScope.launch(Dispatchers.IO){ loadListAsync() }
        else
            loadList()
    }

    private suspend fun loadListAsync(){
        isRefreshing(true)
        presenter.loadListByUser()
        loadList()
    }

    private fun loadList(){
        activity?.runOnUiThread {
            adapter.clear()
            adapter.addAll(GlobalUtils.shoppingLists)
            empty_list.text = getString(R.string.shopping_list_empty_list)
            empty_list.setEmptyList(adapter.itemCount)
        }
        isRefreshing(false)
    }

    private fun onRefreshListener(){
        activity?.runOnUiThread {
            shopping_list_swipe_refresh?.setOnRefreshListener {
                if(jobRefresh?.isActive == true)
                    jobRefresh?.cancel()

                jobRefresh = lifecycleScope.launch(Dispatchers.IO) {
                    refresh()
                }
            }
        }
    }

    private suspend fun refresh(){
        presenter.sendShoppingList()
        loadListAsync()
    }

    override fun initAdapter(){
        shopping_list_recycler_view?.adapter = adapter
    }

    override fun onRecognitionOnErrorEvent(event: RecognitionOnErrorEvent) {
        speak(event.errorMessageStringRes)
    }

    override fun onRecognitionOnResultEvent(event: RecognitionOnResultEvent) {
        with(event) {
            if (recognitionParams?.actionsType == ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST) {
                if (bestResult.yesAnswer()) {
                    navigateToItemsShoppingListFragment(recognitionParams.currentShoppingListId)
                }
                speakOk()
            } else {
                val shoppingList = presenter.createShoppingList(bestResult)
                addItemInAdapter(shoppingList)

                val params = RecognitionParams(ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST, shoppingList.id)
                speakOkAndMore(
                    R.string.shopping_list_redirect_to_item_shopping_list,
                    onSpeakDone = { startRecognition(params) }
                )
            }
        }
    }

    private fun addItemInAdapter(shoppingList: ShoppingList){
        GlobalUtils.shoppingLists.add(shoppingList)
        adapter.add(shoppingList)
        empty_list.setEmptyList(adapter.itemCount)
    }

    override fun onClickFloatingButton(){
        stopAll()
        speak(
            R.string.text_to_speech_title_shopping_list,
            onSpeakDone = {
                startRecognition()
            }
        )
    }

    private fun navigateToItemsShoppingListFragment(shoppingListId: String){
        activity?.runOnUiThread {
            findNavController().navigate(
                ShoppingListFragmentDirections.actionShoppingListFragmentToItemShoppingListFragment(shoppingListId)
            )
        }
    }

    override fun onClickItemList(shoppingListId: String) {
        navigateToItemsShoppingListFragment(shoppingListId)
    }

    override fun onPause() {
        sendShoppingList()
        super.onPause()
    }

    private fun sendShoppingList(){
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.sendShoppingList()
        }
    }
}
