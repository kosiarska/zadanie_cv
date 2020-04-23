package pl.michal.tretowicz.ui.main


import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    private lateinit var adapter: CvAdapter
    private var doubleBackToExitPressedOnce = false

    private var dialog : AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)

        presenter.attachView(this)

        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        toastManager.success(R.string.press_twice_to_exit)

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showData(data: CvResponse) {
        adapter = CvAdapter(this, data)
        list.adapter = adapter
    }

    override fun showNoNetworkDialog() {
        dialog = AlertDialog.Builder(this)
                .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                    presenter.retry()
                }
                .setTitle(R.string.title_no_network)
                .show()
    }

    override fun dismissNoNetworkDialog() {
        dialog?.dismiss()
    }
}
