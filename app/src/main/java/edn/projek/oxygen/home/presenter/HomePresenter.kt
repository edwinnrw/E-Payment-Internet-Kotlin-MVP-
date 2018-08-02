package edn.projek.oxygen.home .presenter

import android.content.Context
import android.widget.Toast
import edn.projek.oxygen.api.Endpoint
import edn.projek.oxygen.api.ServiceHelper
import edn.projek.oxygen.model.GetBillResponse
import edn.projek.oxygen.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(val context: Context, val view: HomeContract.View) : HomeContract.Presenter {

    override fun getBill() {
        val userPreference = UserPreference(context)
        var param :HashMap<String,String> = HashMap()
        param.put("no_customer", userPreference.getProfileData().getValue("no_customer"))
        view.showProgress()
        ServiceHelper.createService(Endpoint::class.java).getTagihan(param).enqueue( object : Callback<GetBillResponse> {
            override fun onFailure(call: Call<GetBillResponse>, t: Throwable) {
                view.dismisProgress()
                view.showMessageFail(t.message.toString())

            }

            override fun onResponse(call: Call<GetBillResponse>?, response: Response<GetBillResponse>) {
                view.dismisProgress()
                if (response.code()==200){
                    if (response.body()?.data?.size!! > 0){
                        view.createList(response.body()!!.data)
                    }else{
                        view.showEmpty()
                    }
                }else{
                    view.showMessageFail(response.message())
                }
            }


        })
    }
}