package pl.michal.tretowicz.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 * Created by Michał Trętowicz
 */
abstract class BaseFragment : androidx.fragment.app.Fragment() {

    abstract fun getLayoutResId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detachView()
    }

    fun activityComponent() = (activity as BaseActivity).activityComponent

    open fun attachView() {

    }

    open fun detachView() {

    }
}