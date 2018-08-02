package edn.projek.oxygen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLoginJson(val no_customer:String, val gender:String,
                         val iden_type:String, val  iden_no:String,
                         val name:String, val email:String,
                         val phone:String, val address:String,
                         val `package`:String, val  device_type:String,
                         val serial_no:String, val device_mount:String)