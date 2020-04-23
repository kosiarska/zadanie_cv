package pl.michal.tretowicz.ui.main


import android.os.Bundle
import android.view.View
import com.github.ajalt.timberkt.e
import kotlinx.android.synthetic.main.activity_main.*
import pl.michal.tretowicz.R
import pl.michal.tretowicz.data.ToastManager
import pl.michal.tretowicz.data.remote.model.CvResponse
import javax.inject.Inject
import pl.michal.tretowicz.ui.base.BaseActivity
import pl.michal.tretowicz.ui.main.adapter.CvAdapter

/**
 * Created by Michał Trętowicz
 */
class MainActivity : BaseActivity(), MainMvpView {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var toastManager: ToastManager

    lateinit var adapter: CvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)

        presenter.attachView(this)

        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showData(data: CvResponse) {
        adapter = CvAdapter(this, data)
        list.adapter = adapter
    }
}
