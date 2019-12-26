package br.com.shoppinglistapp.view.fragment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionListener
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main.shopping_list_layout.*
import java.util.*


class ShoppingListFragment: BaseCollectionFragment(), ShoppingFragmentListClickHandler {

    private val adapter by lazy { ShoppingListAdapter(this) }

    init {
        adapter.setList(GlobalUtils.shoppingLists )
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
//            navigateToItemsShoppingListFragment("")//TEMP
            try{
                RecognitionUtils().startToSpeech()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
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
