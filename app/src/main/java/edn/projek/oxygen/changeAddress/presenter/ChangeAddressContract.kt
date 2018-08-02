package edn.projek.oxygen.changeAddress.presenter

class ChangeAddressContract {
    interface View {
        fun showProgress()
        fun dismissProgress()
        fun showMessageSuccess()
        fun showMessageFailed()
        fun setSpinnerProvince()
        fun setSpinnerCity()
        fun setSpinnerDistrict()
        fun setSpinnerSubDistrict()
    }

    interface Presenter {
        fun checkValidForm(province: String, address: String)
        fun requestChange(province: String, address: String)
        fun requestGetProvince()
        fun requestGetCity(province: String)
        fun requestGetDistrict(city:String)
        fun requestSubDistrict(district:String)
    }

}