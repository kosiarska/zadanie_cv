package pl.michal.tretowicz.ui.main


import pl.michal.tretowicz.data.DataManager
import pl.michal.tretowicz.data.RxEventBus
import pl.michal.tretowicz.data.repository.session.SessionManager
import pl.michal.tretowicz.injection.ConfigPersistent
import pl.michal.tretowicz.ui.base.BasePresenter
import pl.michal.tretowicz.ui.base.MvpView
import javax.inject.Inject

interface MainMvpView : MvpView {

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
    }

}
