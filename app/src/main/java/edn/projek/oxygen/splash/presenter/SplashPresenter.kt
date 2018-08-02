package edn.projek.oxygen.splash.presenter

import android.content.Context
import android.os.Handler
import edn.projek.oxygen.preference.UserPreference
import java.util.*

class SplashPresenter(context: Context, view: SplashContract.View) : SplashContract.Presenter {

    private val SPLASH_TIMER : Long = 1000
    private lateinit var mHandler: Handler
    private lateinit var mmRunnable: Runnable

    var context: Context
    var view: SplashContract.View

    init {
        this.context = context
        this.view = view
    }

    override fun checkIsLogin() {
        mHandler = Handler()
        var userPreference = UserPreference(context)

        mmRunnable = Runnable {
            if (userPreference.getIsLogin()) {
                view.goToLogin()
            } else {
                view.goToMain()
            }
        }
        mHandler.postDelayed(mmRunnable,SPLASH_TIMER)


    }
}