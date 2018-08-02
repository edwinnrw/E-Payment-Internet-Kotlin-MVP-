package edn.projek.oxygen.api

import edn.projek.oxygen.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Endpoint{
    @POST("./pelanggan/login")
    fun login(@Body param:HashMap<String,String>) : Call<GetLoginResponse>

    @POST("./pelanggan/billing")
    fun getTagihan(@Body param: HashMap<String, String>) : Call<GetBillResponse>

    @POST("./pelanggan/channel")
    fun getChannel() : Call<GetChannelResponse>

    @POST("./pelanggan/check_card")
    fun checkCard(@Body param:HashMap<String,String>) : Call<GetCheckCardResponse>

    @POST("./pelanggan/update_status")
    fun payment(@Body param:HashMap<String,String>) : Call<GetPaymentResponse>

    @POST("pelanggan/logout")
    fun logout (@Body param:HashMap<String,String>) : Call<LogoutResponseJson>

}