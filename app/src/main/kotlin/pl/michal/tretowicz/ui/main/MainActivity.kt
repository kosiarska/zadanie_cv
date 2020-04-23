package pl.michal.tretowicz.ui.main


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.michal.tretowicz.R
import pl.michal.tretowicz.data.ToastManager
import javax.inject.Inject
import pl.michal.tretowicz.ui.base.BaseActivity

/**
 * Created by Michał Trętowicz
 */
class MainActivity : BaseActivity(), MainMvpView {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var toastManager: ToastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)

        presenter.attachView(this)

        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
