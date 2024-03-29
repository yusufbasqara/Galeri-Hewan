package org.d3if4076.galeri_hewan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4076.galeri_hewan.data.Hewan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "indraazimi/galeri-hewan/static-api/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface HewanApiService {
    @GET("static-api.json")
    suspend fun getHewan(): List<Hewan>

}
object HewanApi {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)
    }
    fun getHewanUrl(nama: String): String {
        return "$BASE_URL$nama.jpg"
    }

}
enum class ApiStatus { LOADING, SUCCESS, FAILED }