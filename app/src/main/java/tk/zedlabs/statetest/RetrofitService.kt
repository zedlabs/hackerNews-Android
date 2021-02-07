package tk.zedlabs.statetest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    private const val hackerNewsApiBaseUrl = "https://hacker-news.firebaseio.com/v0/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(hackerNewsApiBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}