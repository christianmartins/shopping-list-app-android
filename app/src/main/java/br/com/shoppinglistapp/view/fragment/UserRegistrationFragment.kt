package br.com.shoppinglistapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.*
import kotlinx.android.synthetic.main.user_registration_view_layout.*

class UserRegistrationFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_registration_view_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRegisterButtonClick()
    }

    private fun onRegisterButtonClick() {
        user_registration_view_register.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        if(validateFields()){
            navigateToShoppingList()
        }
    }

    private fun validateFields(): Boolean{
        return user_registration_view_text_input_email.isValidEmail(getEmail())
        && user_registration_view_text_input_password.isValidPassword(getPassword())
        && user_registration_view_text_input_password_repeat.isValidPasswordRepeat(getPasswordRepeat())
        && user_registration_view_text_input_firstName.isValidFirstName(getFirstName())
        && user_registration_view_text_input_last_name.isValidLastName(getLastName())
    }

    private fun getEmail(): String{
        return user_registration_view_edit_text_email.getSafeTextWithTrim()
    }

    private fun getPassword(): String{
        return user_registration_view_edit_text_passowrd.getSafeTextWithTrim()
    }

    private fun getPasswordRepeat(): String{
        return user_registration_view_edit_text_passowrd_repeat.getSafeTextWithTrim()
    }

    private fun getFirstName(): String{
        return user_registration_view_edit_text_first_name.getSafeTextWithTrim()
    }

    private fun getLastName(): String{
        return user_registration_view_edit_text_last_name.getSafeTextWithTrim()
    }

    private fun navigateToShoppingList(){
        activity?.runOnUiThread {
            findNavController().navigate(ShoppingListFragmentDirections.actionGlobalShoppingListFragment())
        }
    }
}