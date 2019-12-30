package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.setEmptyList
import br.com.shoppinglistapp.presenter.ShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.DateUtils
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.enum.ActionType
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
import kotlinx.android.synthetic.main._empty_list_layout.*
import kotlinx.android.synthetic.main.shopping_list_layout.*
import java.util.*


class ShoppingListFragment: BaseCollectionFragment(), ShoppingFragmentListClickHandler, TextToSpeech.OnInitListener {

    private val presenter by lazy { ShoppingListFragmentPresenter() }

    private val adapter by lazy { ShoppingListAdapter(this) }

    private val recognitionUtils = RecognitionUtils()

    private lateinit var  textToSpeech: TextToSpeech

    init {
        adapter.addAll(GlobalUtils.shoppingLists)
    }

    private fun initTextToSpeech (): TextToSpeech{
        return TextToSpeech(context, this).let {
            it.language = Locale.getDefault()
            it.setSpeechRate(1.8F)
            it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        textToSpeech = initTextToSpeech()

        getFab()?.setImageResource(R.drawable.ic_add_white_24dp)
        return inflater.inflate(R.layout.shopping_list_layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        GlobalUtils.currentActionType = ActionType.NONE
        GlobalUtils.currentShoppingListId = ""
    }

    override fun onMessageEvent(event: MessageEvent) {
        super.onMessageEvent(event)
        when(event){
            is RecognitionOnResultEvent -> {
                if(GlobalUtils.fragmentAlive == this@ShoppingListFragment.javaClass.name){
                    with(event){
                        if(GlobalUtils.currentActionType == ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST){
                            GlobalUtils.currentActionType = ActionType.NONE
                            GlobalUtils.currentShoppingListId = ""

                            if(bestResult.equals("SIM", true)){
                                navigateToItemsShoppingListFragment(GlobalUtils.currentShoppingListId)
                            }
                        }
                        else{
                            val shoppingList = presenter.getData().copy(
                                title = bestResult
                            )

                            GlobalUtils.shoppingLists.add(shoppingList)
                            adapter.add(shoppingList)
                            empty_list.setEmptyList(adapter.itemCount)
                            speak(R.string.shopping_list_redirect_to_item_shopping_list)
                            GlobalUtils.currentActionType = ActionType.REDIRECT_TO_ITEM_SHOPPING_LIST
                            GlobalUtils.currentShoppingListId = shoppingList.id
                        }
                    }
                }
            }
        }
    }

    override fun onInit(p0: Int) {
        if(p0 == TextToSpeech.SUCCESS){
            print("${this.javaClass.name}- onInit: success")
        }else{
            print("${this.javaClass.name}- onInit: failed")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        onClickFloatingButton()
        empty_list.text = getString(R.string.shopping_list_empty_list)
        empty_list.setEmptyList(adapter.itemCount)
    }

    private fun initAdapter(){
        shopping_list_recycler_view?.adapter = adapter
    }

    private fun onClickFloatingButton(){
        getFab()?.setOnClickListener {
            speak(R.string.text_to_speech_title_shopping_list)
        }
    }

    private fun speak(@StringRes stringRes: Int, params: Bundle? = null){
        activity?.runOnUiThread {
            try{
                textToSpeech.speak(getString(stringRes), TextToSpeech.QUEUE_FLUSH, params, DateUtils.getTimeStamp().toString())
                textToSpeech.setOnUtteranceProgressListener(TextToSpeechCustom())
                textToSpeech.Engine()
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

    inner class TextToSpeechCustom: UtteranceProgressListener(){
        override fun onDone(p0: String?) {
            activity?.runOnUiThread {
                recognitionUtils.startToSpeech()
            }
        }

        override fun onError(p0: String?) {
            print("${this.javaClass.name} - TextToSpeechCustom - onError:")
        }

        override fun onStart(p0: String?) {
            print("${this.javaClass.name} - TextToSpeechCustom - onStart:")
        }
    }
}
