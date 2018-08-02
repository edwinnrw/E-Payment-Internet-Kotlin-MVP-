package edn.projek.oxygen.home.presenter

import edn.projek.oxygen.model.Bill

class HomeContract{
    interface View{
        fun showProgress()
        fun dismisProgress()
        fun createList(data: List<Bill>)
        fun showEmpty();
        fun showMessageFail(message:String)
    }
    interface Presenter{
        fun getBill()
    }
}