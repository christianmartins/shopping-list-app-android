package br.com.shoppinglistapp.view.fragment

import androidx.fragment.app.Fragment
import br.com.shoppinglistapp.utils.GlobalUtils
import br.com.shoppinglistapp.utils.event.MessageEvent
import br.com.shoppinglistapp.view.activity.MainActivity
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

    protected fun getFab(): FloatingActionButton?{
        return getWeakMainActivity().get()?.fab
    }
}