package my.android.baahimusicassignment.api


import my.android.baahimusicassignment.models.BAAHIBYXOMOY
import my.android.baahimusicassignment.models.BAAHIBYXOMOYX
import my.android.baahimusicassignment.models.BAAHIBYXOMOYXX
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("hbAZZiJx")
    suspend fun getTrendingSongs(): Map<String, List<BAAHIBYXOMOY>>

    @GET("CwyxSJ8K")
    suspend fun getJustReleased(): Map<String, List<BAAHIBYXOMOYX>>

    @GET("1dN1EkTY")
    suspend fun getRecommendedArtists(): Map<String, List<BAAHIBYXOMOYXX>>
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pastebin.com/raw/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}