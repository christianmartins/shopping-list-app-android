package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.presenter.ShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.DateUtils
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.RecognitionUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.utils.event.RecognitionOnResultEvent
import br.com.shoppinglistapp.utils.interfaces.ShoppingFragmentListClickHandler
import br.com.shoppinglistapp.view.adapter.ShoppingListAdapter
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

    override fun onMessageEvent(event: MessageEvent) {
        super.onMessageEvent(event)
        when(event){
            is RecognitionOnResultEvent -> {
                if(GlobalUtils.fragmentAlive == this.javaClass.name){
                    val shoppingList = presenter.getData().copy(
                        title = event.bestResult
                    )

                    GlobalUtils.shoppingLists.add(shoppingList)
                    adapter.add(shoppingList)
                    navigateToItemsShoppingListFragment(shoppingList.id)
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
    }

    private fun initAdapter(){
        shopping_list_recycler_view?.adapter = adapter
    }

    private fun onClickFloatingButton(){
        getFab()?.setOnClickListener {
            activity?.runOnUiThread {
                try{
                    textToSpeech.speak(getString(R.string.text_to_speech_title_shopping_list), TextToSpeech.QUEUE_FLUSH, null, DateUtils.getTimeStamp().toString())
                    textToSpeech.setOnUtteranceProgressListener(TextToSpeechCustom())
                    textToSpeech.Engine()
                }catch (e: Exception){
                    e.printStackTrace()
                }
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
