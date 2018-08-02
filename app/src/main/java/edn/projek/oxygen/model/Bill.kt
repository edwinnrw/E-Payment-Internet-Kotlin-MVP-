package edn.projek.oxygen.model

import java.io.Serializable

data class Bill(val name: String, val id_billing: String,
                val bill_code: String, val bill_cycle : String, val periode:String,
                val bill_date:String,val due_date:String, val `package`:String,
                val total_bill: Double, val status : String) : Serializable
