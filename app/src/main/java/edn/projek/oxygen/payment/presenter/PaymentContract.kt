package edn.projek.oxygen.payment.presenter

import edn.projek.oxygen.model.ChannelPayment

class PaymentContract {
    interface View{
        fun setSpinnerChannel(data: List<ChannelPayment>)
        fun setDataBill(data : HashMap<String,String>)

        fun requestFailed(message : String)
        fun requestSuccess()
        fun validFailed(message: String)
        fun validSuccess()
        fun showProgress()
        fun dismissProgress()


    }
    interface Presenter{
        fun requestGetChannel()
        fun requestCheckCard(expDate:String, expDateYear: String, cvv: String, noCredit: String)
        fun getDataTagihan()
        fun checkValidField(paymentMethod: String, noCredit: String, cvv:String, expDate:String,
                            expDateYear:String)
    }

}