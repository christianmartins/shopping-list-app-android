package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment: BaseFragment() {

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
            Toast.makeText(context!!, "logando...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun visitorEnterClick(){
        visitor_enter.setOnClickListener {
            findNavController().navigate(ShoppingListFragmentDirections.actionGlobalShoppingListFragment())
        }
    }
}