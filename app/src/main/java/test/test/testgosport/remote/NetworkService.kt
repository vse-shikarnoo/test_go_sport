package test.test.testgosport.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import test.test.testgosport.BuildConfig
import test.test.testgosport.model.CategorySearchResponse
import test.test.testgosport.model.MealSearchResponse

interface NetworkService {

    //https://www.themealdb.com/api/json/v1/1/search.php?s
    @GET("search.php?s")
    suspend fun getMeals():MealSearchResponse

    @GET("categories.php")
    suspend fun getCategories():CategorySearchResponse

    companion object{
        fun create(): NetworkService {
            val okHttpClient = OkHttpClient.Builder()
                .build()


            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NetworkService::class.java)
        }
    }
}