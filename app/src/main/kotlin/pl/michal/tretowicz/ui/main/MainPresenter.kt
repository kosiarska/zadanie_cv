package pl.michal.tretowicz.ui.main


import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import pl.michal.tretowicz.data.DataManager
import pl.michal.tretowicz.data.RxEventBus
import pl.michal.tretowicz.data.remote.model.CvResponse
import pl.michal.tretowicz.data.repository.session.SessionManager
import pl.michal.tretowicz.injection.ConfigPersistent
import pl.michal.tretowicz.ui.base.BasePresenter
import pl.michal.tretowicz.ui.base.MvpView
import javax.inject.Inject

interface MainMvpView : MvpView {
    fun showProgress(show: Boolean)
    fun showData(data: CvResponse)

}
/**
 * Created by Michał Trętowicz
 */
@ConfigPersistent
class MainPresenter
@Inject
constructor(private val rxEventBus: RxEventBus, private val sessionManager: SessionManager, private val dataManager: DataManager) : BasePresenter<MainMvpView>() {


    override fun attachView(view: MainMvpView) {
        super.attachView(view)

        view.showProgress(true)
        dataManager.getCvData()
                .subscribeBy(
                        onNext = {
                            view.showData(it)
                            view.showProgress(false)
                        },
                        onError = {

                        }
                ).addTo(subscriptions)
    }

}
