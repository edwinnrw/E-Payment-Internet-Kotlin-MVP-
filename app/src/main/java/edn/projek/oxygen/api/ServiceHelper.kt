package edn.projek.oxygen.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHelper {
    private var BASE_URL: String = "http://api.edwinnurwansyah.com/oxygen/index.php/"
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var builder : Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    fun  <S> createService (serviceClass: Class<S>) : S {
        return  createService(serviceClass, null)

    }
    fun <S> createService(serviceClass: Class<S>, token: String?) : S {
        if (token != null){
            httpClient.addInterceptor({chain ->
                val original : Request =chain.request()

                val requestBuilder : Request.Builder = original.newBuilder()
                        .header("Authorization", "Bearer "+token)
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                val request : Request = requestBuilder.build()
                chain.proceed(request)

            })

        }
        val client : OkHttpClient = httpClient.build()
        val retrofit : Retrofit = builder.client(client).build()
        return  retrofit.create(serviceClass)
    }
}