package edn.projek.oxygen.login.presenter

class LoginContract {
    interface View{
        fun gotoMain()
        fun showProgress()
        fun dismisProgress()
        fun showFailed(message :String)
        fun vaildSuccess()
        fun validFailed(message: String)
    }
    interface Presenter{
        fun requestLogin(noCustomer : String, password:String)
        fun checkValidField(noCustomer: String, password: String)
    }
}