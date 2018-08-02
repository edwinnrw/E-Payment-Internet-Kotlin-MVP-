package edn.projek.oxygen.home

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import edn.projek.oxygen.R
import edn.projek.oxygen.home.presenter.MainContract
import edn.projek.oxygen.home.presenter.MainPresenter
import edn.projek.oxygen.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, MainContract.View {


    lateinit var  progressDialog : ProgressDialog
    lateinit var presenter : MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= MainPresenter(this,this)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,HomeFragment.newInstance())
                .commit()
        navigation.setOnNavigationItemSelectedListener(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var  selectedFragment : Fragment? = null
        when (item.itemId){
            R.id.navigation_home ->  selectedFragment = HomeFragment.newInstance()
            R.id.navigation_user -> selectedFragment = ProfileFragment.newInstance()
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,selectedFragment)
                .commit()
        return  true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu) //your file name
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.ic_logout -> presenter.requestLogout()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun showProgress() {
        progressDialog.show()
    }

    override fun dismisProgress() {
        progressDialog.dismiss()
    }

    override fun requestLogoutFailed(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun requestLogoutSuccess() {
        startActivity(Intent(this,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
