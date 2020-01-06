package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.View

abstract class BaseCollectionFragment: BaseSpeakAndRecognitionFragment() {

    abstract fun initAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myFabOnClickListener()
    }

    private fun myFabOnClickListener(){
        getFab()?.setOnClickListener {
            onClickFloatingButton()
        }
    }

    abstract fun onClickFloatingButton ()
}