package br.com.shoppinglistapp.view.activity

import android.os.Bundle
import br.com.shoppinglistapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : BaseActivity() {

    val fab by lazy {
        findViewById<FloatingActionButton?>(R.id.fab)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideActionBar()
    }
}
