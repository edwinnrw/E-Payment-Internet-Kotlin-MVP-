package edn.projek.oxygen.home.presenter

class MainContract {
    interface View{
        fun showProgress()
        fun dismisProgress()
        fun requestLogoutFailed(message:String)
        fun requestLogoutSuccess()

    }

    interface Presenter{
        fun requestLogout()

    }
}