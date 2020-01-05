package br.com.shoppinglistapp.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.setupBottomNavigationBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    private var currentNavController: LiveData<NavController>? = null

    val fab by lazy {
        findViewById<FloatingActionButton?>(R.id.fab)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideActionBar()

        if (savedInstanceState == null) {
            currentNavController = setupBottomNavigationBar()
        }

        onBottomNavigationMenuItemReselect()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        currentNavController = setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun onBottomNavigationMenuItemReselect(){
        bottom_app_bar.setOnNavigationItemReselectedListener { menuItem ->
            findNavController(R.id.nav_host_container).navigate(menuItem.itemId)
        }
    }
}

