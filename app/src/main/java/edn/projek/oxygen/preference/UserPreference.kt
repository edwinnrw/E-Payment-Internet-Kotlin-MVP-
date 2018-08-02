package edn.projek.oxygen.preference

import android.content.Context
import android.content.SharedPreferences
import edn.projek.oxygen.model.DataLoginJson

class  UserPreference (context : Context){
    companion object {
        private val LOGIN= "LOGIN"
        val USER_PREF= "USER_PREF"
    }
    private var preference : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        preference= context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
    }
    fun setDataProfile(value: DataLoginJson?){
        editor= preference.edit()
        editor.putString("no_customer", value?.no_customer)
        editor.putString("gender", value?.gender)
        editor.putString("iden_type", value?.iden_type)
        editor.putString("iden_no",value?.iden_no)
        editor.putString("name",value?.name)
        editor.putString("email", value?.email)
        editor.putString("phone", value?.phone)
        editor.putString("package", value?.`package`)
        editor.putString("device_type", value?.device_type)
        editor.putString("serial_no", value?.serial_no)
        editor.putString("address", value?.address)
        editor.apply()
    }
    fun setIsLogin(isLogin : Boolean){
        editor =preference.edit()
        editor.putBoolean(LOGIN,isLogin)
        editor.apply()
    }

    fun getIsLogin() : Boolean{
        return preference.getBoolean(LOGIN, true)
    }
    fun  getProfileData (): HashMap<String,String>{
        var data : HashMap<String,String> = HashMap()
        data.put("no_customer", preference.getString("no_customer",""))
        data.put("gender", preference.getString("gender",""))
        data.put("iden_type", preference.getString("iden_type",""))
        data.put("iden_no", preference.getString("iden_no",""))
        data.put("name", preference.getString("name",""))
        data.put("address", preference.getString("address",""))
        data.put("phone", preference.getString("phone",""))
        data.put("package", preference.getString("package",""))
        data.put("email", preference.getString("no_customer",""))
        data.put("device_type", preference.getString("device_type",""))
        data.put("serial_no", preference.getString("serial_no",""))

        return  data

    }
}