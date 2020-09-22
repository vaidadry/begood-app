package vaida.dryzaite.begoodapp.data.remote.responses

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import vaida.dryzaite.begoodapp.BuildConfig

interface PixabayApi {

    @GET("/api/")
    suspend fun searchForImages(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ) : Response<ImageResponse>
}