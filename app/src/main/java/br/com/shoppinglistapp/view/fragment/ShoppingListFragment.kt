package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.setEmptyList
import br.com.shoppinglistapp.presenter.ShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.enum.ActionType
import br.com.shoppinglistapp.utils.event.RecognitionOnErrorEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main._empty_list_layout.*
import kotlinx.android.synthetic.main.shopping_list_layout.*


class ShoppingListFragment: BaseCollectionFragment(), ShoppingFragmentListClickHandler{

    private val presenter by lazy { ShoppingListFragmentPresenter() }

    private val adapter by lazy { ShoppingListAdapter(this) }

    init {
        adapter.addAll(GlobalUtils.shoppingLists)
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
        onClickFloatingButton()
        empty_list.text = getString(R.string.shopping_list_empty_list)
        empty_list.setEmptyList(adapter.itemCount)
    }

    override fun initAdapter(){
        shopping_list_recycler_view?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        GlobalUtils.currentActionType = ActionType.NONE
        GlobalUtils.currentShoppingListId = ""
    }

    override fun onRecognitionOnResultEvent(event: RecognitionOnResultEvent) {
        with(event) {
            if (GlobalUtils.currentActionType == ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST) {
                GlobalUtils.currentActionType = ActionType.NONE
                GlobalUtils.currentShoppingListId = ""
                if (bestResult.equals(getString(R.string.speak_confirm), true)) {
                    navigateToItemsShoppingListFragment(GlobalUtils.currentShoppingListId)
                }
                speak(R.string.speak_ok)
            } else {
                hasStartToSpeech = true
                val shoppingList = presenter.getData().copy(title = bestResult)

                GlobalUtils.shoppingLists.add(shoppingList)
                adapter.add(shoppingList)
                empty_list.setEmptyList(adapter.itemCount)
                speak(R.string.shopping_list_redirect_to_item_shopping_list)
                GlobalUtils.currentActionType = ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST
                GlobalUtils.currentShoppingListId = shoppingList.id
            }
        }
    }

    override fun onRecognitionOnErrorEvent(event: RecognitionOnErrorEvent) {
        speak(getString(R.string.element_arg, event.errorMessage))
    }

    override fun onClickFloatingButton(){
        hasStartToSpeech = true
        speak(R.string.text_to_speech_title_shopping_list)
    }

    private fun navigateToItemsShoppingListFragment(shoppingListId: String){
        findNavController().navigate(
            ShoppingListFragmentDirections.actionShoppingListFragmentToItemShoppingListFragment(shoppingListId)
        )
    }

    override fun onClickItemList(shoppingListId: String) {
        navigateToItemsShoppingListFragment(shoppingListId)
    }
}
