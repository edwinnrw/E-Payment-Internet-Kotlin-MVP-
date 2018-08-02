package edn.projek.oxygen.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import edn.projek.oxygen.home.MainActivity
import edn.projek.oxygen.R
import edn.projek.oxygen.login.LoginActivity
import edn.projek.oxygen.splash.presenter.SplashContract
import edn.projek.oxygen.splash.presenter.SplashPresenter

class SplashActivity : AppCompatActivity(), SplashContract.View {


    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter= SplashPresenter(this,this)
        presenter.checkIsLogin()
    }

    override fun goToLogin() {
        startActivity(Intent(this,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ))
        finish()
    }

    override fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()

    }
}
