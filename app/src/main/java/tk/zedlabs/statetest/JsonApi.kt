package tk.zedlabs.statetest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonApi {

    /* List of story ids's, iterate over the list to generate actual story objects */
    @GET("/v0/topstories.json")
    suspend fun getStoryIdList(): Response<List<Long>>

    @GET("/v0/item/{id}.json")
    suspend fun getStory(
        @Path("id") id: String
    ): Response<Story>
}