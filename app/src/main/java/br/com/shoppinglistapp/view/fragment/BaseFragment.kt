package br.com.shoppinglistapp.view.fragment

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.shoppinglistapp.R
import br.com.shoppinglistapp.extensions.hide
import br.com.shoppinglistapp.extensions.show
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference


open class BaseFragment: Fragment(){

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: MessageEvent) {}

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        GlobalUtils.fragmentAlive = this.javaClass.name
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    private fun getWeakMainActivity(): WeakReference<MainActivity?> {
        return WeakReference(activity as? MainActivity)
    }

    fun getFab(): FloatingActionButton?{
        return getWeakMainActivity().get()?.fab
    }

    private fun getBottomNavigationMenuView(): BottomNavigationView?{
        return getWeakMainActivity().get()?.bottomNavigationMenu
    }

    fun hideFabAndBottomNav(){
        getFab()?.hide()
        getBottomNavigationMenuView()?.hide()
    }

    fun showFabAndBottomNav(){
        getFab()?.show()
        getBottomNavigationMenuView()?.show()
    }

    fun showMessage(
        @StringRes resStringTitle: Int,
        @StringRes resStringMessage: Int,
        @StringRes resStringPositiveButton: Int = R.string.ok_confirm
    ){
        confirmMessage(
            resStringTitle = resStringTitle,
            resStringMessage = resStringMessage,
            resStringPositiveButton = resStringPositiveButton,
            resStringNegativeButton = R.string.nothing,
            positiveClickListener = DialogInterface.OnClickListener { dialogInterface, _ -> dialogInterface.dismiss()},
            negativeClickListener = null
        )
    }

    @Suppress("SameParameterValue")
    private fun confirmMessage(
        @StringRes resStringTitle: Int,
        @StringRes resStringMessage: Int,
        @StringRes resStringPositiveButton: Int,
        @StringRes resStringNegativeButton: Int,
        positiveClickListener: DialogInterface.OnClickListener,
        negativeClickListener: DialogInterface.OnClickListener?
    ){
        activity?.runOnUiThread {
            context?.let {
                MaterialAlertDialogBuilder(it)
                .setTitle(resStringTitle)
                .setMessage(resStringMessage)
                .setPositiveButton(resStringPositiveButton, positiveClickListener).apply {
                    if(negativeClickListener != null)
                        setNegativeButton(resStringNegativeButton, negativeClickListener)
                }.show()
            }
        }
    }

    fun showProgressBarDialog(context: Context): AlertDialog{
        return MaterialAlertDialogBuilder(context)
        .setBackground(ColorDrawable(Color.TRANSPARENT))
        .setView(R.layout._progress_bar)
        .show()
    }
}