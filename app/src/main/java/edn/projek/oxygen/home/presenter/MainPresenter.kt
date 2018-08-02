package edn.projek.oxygen.home.presenter

import android.content.Context
import edn.projek.oxygen.api.Endpoint
import edn.projek.oxygen.api.ServiceHelper
import edn.projek.oxygen.model.LogoutResponseJson
import edn.projek.oxygen.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val context: Context, val  view:MainContract.View) : MainContract.Presenter {
    override fun requestLogout() {
        view.showProgress()
        var userPreference = UserPreference(context)
        var param : HashMap<String,String> = HashMap()
        param.put("no_customer", userPreference.getProfileData().getValue("no_customer"))
        ServiceHelper.createService(Endpoint::class.java).logout(param).enqueue(object : Callback<LogoutResponseJson>{
            override fun onFailure(call: Call<LogoutResponseJson>?, t: Throwable?) {
                view.dismisProgress()
                t?.message.let { view.requestLogoutFailed(it.toString()) }

            }

            override fun onResponse(call: Call<LogoutResponseJson>?, response: Response<LogoutResponseJson>?) {
                view.dismisProgress()
                response?.body()?.let {
                    if (it.message.contains("success",true)){
                        view.requestLogoutSuccess()
                        userPreference.setIsLogin(true)
                    }else{
                        view.requestLogoutFailed("Logout Gagal")
                    }
                }
            }

        })

    }
}