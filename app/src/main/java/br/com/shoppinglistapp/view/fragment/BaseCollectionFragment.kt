package br.com.shoppinglistapp.view.fragment

import br.com.shoppinglistapp.view.activity.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.ref.WeakReference

abstract class BaseCollectionFragment: BaseFragment() {

    private fun getWeakMainActivity(): WeakReference<MainActivity?> {
        return WeakReference(activity as? MainActivity)
    }

    protected fun getFab(): FloatingActionButton?{
        return getWeakMainActivity().get()?.fab
    }
}