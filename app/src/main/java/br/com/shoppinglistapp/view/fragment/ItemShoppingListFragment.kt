package br.com.shoppinglistapp.view.fragment

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.data.model.ItemShoppingList
import br.com.shoppinglistapp.presenter.ItemShoppingListFragmentPresenter
import br.com.shoppinglistapp.utils.interfaces.ItemShoppingListListeners
import br.com.shoppinglistapp.view.adapter.ItemShoppingListAdapter
import kotlinx.android.synthetic.main.item_shopping_list_layout.*
import java.util.*

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
        item_shopping_list_recycler_view?.adapter = adapter
    }

    override fun deleteItem(item: ItemShoppingList) {
        adapter.remove(item)
    }

    private fun onClickAddItem(){
        getFab()?.setOnClickListener {
            promptSpeechInput()
        }
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                context,
                getString(R.string.speech_not_supported),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //TEST
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    result?.let {resultSpeechToTextList ->
                        adapter.add(getItem(resultSpeechToTextList))
                        Log.d("Texto encontrado:", getText(resultSpeechToTextList))
                    }
                }
            }
        }
    }

    private fun getItem(resultSpeechToTextList: List<String>): ItemShoppingList{
        val item = presenter.getData(1)
        item[0].description = resultSpeechToTextList[0]
        return item[0]
    }

    private fun getText(resultSpeechToTextList: List<String>): String{
        var text = ""
        resultSpeechToTextList.forEach {
            text += "$it \n"
        }
        return text
    }

    private fun showToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQ_CODE_SPEECH_INPUT = 100
    }
}