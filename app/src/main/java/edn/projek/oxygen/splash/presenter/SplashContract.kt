package edn.projek.oxygen.splash.presenter

class SplashContract {
    interface  View{
        fun goToLogin()
        fun goToMain()
    }

    interface Presenter{
        fun checkIsLogin ()

    }
}