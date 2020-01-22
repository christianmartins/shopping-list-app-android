package br.com.shoppinglistapp.view.utils

import android.content.Context
import android.view.View
import android.widget.ScrollView
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.getSafeTextWithTrim
import br.com.shoppinglistapp.extensions.nonNullable
import kotlinx.android.synthetic.main._text_input_edit_text_layout.view.*

class CustomTextInputLayout(context: Context): ScrollView(context) {

    init {
        View.inflate(context, R.layout._text_input_edit_text_layout, this)
    }

    fun getText(): String{
        return _text_input_edit_text?.getSafeTextWithTrim().nonNullable()
    }

    fun setText(text: String){
        _text_input_edit_text?.setText(text)
    }

    fun setHint(hint: String){
        _text_input_layout?.hint = hint
    }

}
