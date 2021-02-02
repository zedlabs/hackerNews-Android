package tk.zedlabs.statetest

import retrofit2.Response
import retrofit2.http.GET

interface JsonApi {

    /* List of story ids's, iterate over the list to generate actual story objects */
    @GET("/topstories.json")
    suspend fun getImageList(): Response<List<Long>>
}