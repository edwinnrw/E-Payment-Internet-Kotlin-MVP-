package edn.projek.oxygen.payment.presenter

import edn.projek.oxygen.model.Invoice

class ConfirmCotract {
    interface View {
        fun showProgress()
        fun dismisProgress()
        fun requestFailed(message: String)
        fun requestSuccess(message: String, data: Invoice)
    }

    interface Presenter {
        fun requestPayment(cardNumber: String, idBilling: String, totalBill: String)

    }
}