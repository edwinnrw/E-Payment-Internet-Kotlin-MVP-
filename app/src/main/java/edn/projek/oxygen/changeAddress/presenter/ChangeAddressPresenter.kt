package edn.projek.oxygen.changeAddress.presenter

import android.content.Context
import android.view.View

class ChangeAddressPresenter (val context: Context, val view: ChangeAddressContract.Presenter) : ChangeAddressContract.Presenter {


    override fun requestGetCity(province: String) {
    }

    override fun requestGetDistrict(city: String) {
    }

    override fun requestSubDistrict(district: String) {
    }

    override fun requestGetProvince() {
    }

    override fun checkValidForm(province: String, address: String) {


    }

    override fun requestChange(province: String, address: String) {
    }
}