package edn.projek.oxygen.payment.presenter

import android.content.Context
import edn.projek.oxygen.api.Endpoint
import edn.projek.oxygen.api.ServiceHelper
import edn.projek.oxygen.model.GetChannelResponse
import edn.projek.oxygen.model.GetCheckCardResponse
import edn.projek.oxygen.preference.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentPresenter(val context: Context, val view: PaymentContract.View) : PaymentContract.Presenter {


    override fun getDataTagihan() {
        var userPreference = UserPreference(context)
        view.setDataBill(userPreference.getProfileData())
    }


    override fun requestGetChannel() {
        view.showProgress()
        ServiceHelper.createService(Endpoint::class.java).getChannel().enqueue(object : Callback<GetChannelResponse> {


            override fun onResponse(call: Call<GetChannelResponse>, response: Response<GetChannelResponse>?) {
                view.dismissProgress()
                response?.code().let {
                    if (it == 200) {
                        response?.body()?.data?.let { view.setSpinnerChannel(it) }
                    } else {
                        response?.message()?.let { view.requestFailed(it) }

                    }
                }

            }

            override fun onFailure(call: Call<GetChannelResponse>, t: Throwable?) {
                t?.message.let { view.requestFailed(t.toString()) }
                view.dismissProgress()
            }

        })

    }

    override fun requestCheckCard(expDate: String, expDateYear: String, cvv: String, noCredit: String) {
        var userPreference = UserPreference(context)
        var param: HashMap<String, String> = HashMap()
        param.put("no_customer", userPreference.getProfileData().getValue("no_customer"))
        param.put("no_kartu_kredit",noCredit )
        param.put("bulan", expDate)
        param.put("tahun", expDateYear)
        param.put("cvv", cvv)
        view.showProgress()
        ServiceHelper.createService(Endpoint::class.java).checkCard(param).enqueue(object : Callback<GetCheckCardResponse>{
            override fun onFailure(call: Call<GetCheckCardResponse>?, t: Throwable?) {
                view.dismissProgress()
                t?.message.let { view.requestFailed(it.toString()) }

            }

            override fun onResponse(call: Call<GetCheckCardResponse>?, response: Response<GetCheckCardResponse>?) {
                view.dismissProgress()
                response?.body()?.message.let {
                    if (it.toString().contentEquals("success")){
                        view.requestSuccess()
                    }else{
                        view.requestFailed(it.toString())
                    }
                }
            }

        })



    }

    override fun checkValidField(paymentMethod: String, noCredit: String, cvv: String, expDate: String, expDateYear: String) {
        if (!paymentMethod.contentEquals("Choose Channel")) {
            if (!noCredit.contentEquals("") && !cvv.contentEquals("")) {
                view.validSuccess()
            } else {
                view.validFailed("Anda Belum Melengkapi Data Kartu Kredit")
            }
        } else {
            view.validFailed("Anda Belum Memilih Metode Pembayaran")

        }
    }
}