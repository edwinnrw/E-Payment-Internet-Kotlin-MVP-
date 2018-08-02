package edn.projek.oxygen.home.presenter

import android.content.Context
import edn.projek.oxygen.preference.UserPreference

class ProfilePresenter (val context: Context, val view:ProfileContract.View) : ProfileContract.Presenter {
    override fun getDataProfile() {
        var userPreference = UserPreference(context)
        view.setDataProfile(userPreference.getProfileData())
    }
}