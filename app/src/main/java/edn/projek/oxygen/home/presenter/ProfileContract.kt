package edn.projek.oxygen.home.presenter

class ProfileContract {
    interface View{
        fun setDataProfile(data : HashMap<String,String>)
    }
    interface Presenter{
        fun getDataProfile()
    }
}