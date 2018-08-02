package edn.projek.oxygen.model

data class GetBillResponse (val message : String,
                            val data : List<Bill>)