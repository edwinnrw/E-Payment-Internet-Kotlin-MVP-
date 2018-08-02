package edn.projek.oxygen.payment.presenter

import android.content.Context
import android.widget.Toast
import edn.projek.oxygen.api.Endpoint
import edn.projek.oxygen.api.ServiceHelper
import edn.projek.oxygen.model.GetPaymentResponse
import edn.projek.oxygen.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmPaymentPresenter (val  context: Context, val view: ConfirmCotract.View) : ConfirmCotract.Presenter{
    override fun requestPayment(cardNumber: String, idBilling: String, totalBill: String) {
        var param : HashMap<String,String> = HashMap()
        var userPreference = UserPreference(context)
        param.put("status","paid")
        param.put("id_billing", idBilling)
        param.put("total_bill", totalBill)
        param.put("no_kartu_kredit", cardNumber)
        param.put("payment_channel", "1")
        param.put("no_customer", userPreference.getProfileData().getValue("no_customer"))
        view.showProgress()
        ServiceHelper.createService(Endpoint::class.java).payment(param).enqueue(object : Callback<GetPaymentResponse> {
            override fun onFailure(call: Call<GetPaymentResponse>?, t: Throwable?) {
                t?.message.let { view.requestFailed(it.toString()) }
                view.dismisProgress()

            }

            override fun onResponse(call: Call<GetPaymentResponse>?, response: Response<GetPaymentResponse>?) {
                view.dismisProgress()
                response?.body()?.let {
                    if (it.message.contains("fail", true)){
                        view.requestFailed("Unknown Error")
                    }else{
                        view.requestSuccess("Tranaksi Berhasil", it.data)
                    }
                }
            }

        })
    }

}