package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.getSafeText
import br.com.shoppinglistapp.extensions.nonNullable
import br.com.shoppinglistapp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.login_fragment_layout.*
import kotlinx.coroutines.launch

class LoginFragment: BaseFragment() {

    private val presenter by lazy {
        LoginPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hideFabAndBottomNav()
        return inflater.inflate(R.layout.login_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visitorEnterClick()
        login()
    }

    private fun login(){
        login_enter.setOnClickListener {
            val alertDialog = showProgressBarDialog(context!!)
            val userName = login_edit_text_email?.getSafeText().nonNullable().trim()
            val password = login_edit_text_passowrd?.getSafeText().nonNullable().trim()

            lifecycleScope.launch {
                val isSuccess = presenter.loginAsync(userName, password).await()
                if(isSuccess){
                    activity?.runOnUiThread {
                        navigateToShoppingListFragment()
                    }
                }else{
                    showMessage(R.string.generic_dialog_title, R.string.login_error)
                }
                activity?.runOnUiThread {
                    alertDialog.hide()
                }
            }
        }
    }

    private fun visitorEnterClick(){
        visitor_enter.setOnClickListener {
            navigateToShoppingListFragment()
        }
    }

    private fun navigateToShoppingListFragment(){
        findNavController().navigate(ShoppingListFragmentDirections.actionGlobalShoppingListFragment())
    }
}