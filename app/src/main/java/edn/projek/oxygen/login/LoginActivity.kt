package edn.projek.oxygen.login

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import edn.projek.oxygen.home.MainActivity
import edn.projek.oxygen.R
import edn.projek.oxygen.login.presenter.LoginContract
import edn.projek.oxygen.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {



    private  lateinit var presenter : LoginPresenter
    private lateinit var progress : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter= LoginPresenter(this,this)
        btnLogin.setOnClickListener(this)
        progress = ProgressDialog(this)
        progress.setMessage("Loading...")
        progress.setCancelable(false)

    }

    override fun onClick(view: View) {
        presenter.checkValidField(noCustomer.text.toString(), password.text.toString())
    }
    override fun showProgress() {
        progress.show()
    }

    override fun dismisProgress() {
        progress.dismiss()
    }

    override fun showFailed(message: String) {
    }

    override fun gotoMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
    override fun vaildSuccess() {
        presenter.requestLogin(noCustomer.text.toString(),password.text.toString())

    }

    override fun validFailed(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }
}
