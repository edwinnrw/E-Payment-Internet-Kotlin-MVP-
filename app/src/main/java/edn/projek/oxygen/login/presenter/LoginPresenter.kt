package edn.projek.oxygen.login.presenter

import android.content.Context
import edn.projek.oxygen.api.Endpoint
import edn.projek.oxygen.api.ServiceHelper
import edn.projek.oxygen.model.GetLoginResponse
import edn.projek.oxygen.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val context: Context,val view: LoginContract.View) : LoginContract.Presenter {


    override fun requestLogin(noCustomer: String, password: String) {
        val userPreference:UserPreference= UserPreference(context)
        val param: HashMap<String, String> = HashMap()
        param.put("no_customer", noCustomer)
        param.put("password", password)
        view?.showProgress()
        ServiceHelper.createService(Endpoint::class.java, "").login(param).enqueue(object : Callback<GetLoginResponse> {

            override fun onResponse(call: Call<GetLoginResponse>?, response: Response<GetLoginResponse>) {
                view?.dismisProgress()
                if (response.code() == 200) {
                    userPreference.setDataProfile(response.body()?.data)
                    userPreference.setIsLogin(false)
                    view?.gotoMain()

                } else {

                }
            }

            override fun onFailure(call: Call<GetLoginResponse>?, t: Throwable) {
                view?.dismisProgress()
                view?.showFailed(t.message.toString())
            }

        })

    }

    override fun checkValidField(noCustomer: String, password: String) {
        if (!noCustomer.trim().contentEquals("") && !password.trim().contentEquals("")) {

            view?.vaildSuccess()

        } else {
            if (noCustomer.trim().contentEquals("") && password.trim().contentEquals("")) {
                view?.validFailed("No Customer dan Password Tidak Boleh Kosong")
            } else {
                if (noCustomer.trim().contentEquals("")) {
                    view?.validFailed("No Customer Tidak Boleh Kosong")
                }
                if (password.trim().contentEquals("")) {
                    view?.validFailed("Password Tidak Boleh Kosong")
                }

            }
        }

    }
}