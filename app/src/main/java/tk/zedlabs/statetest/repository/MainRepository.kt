package tk.zedlabs.statetest.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import tk.zedlabs.statetest.data.JsonApi
import tk.zedlabs.statetest.model.Story
import javax.inject.Inject
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class MainRepository @Inject constructor(
    private val api: JsonApi
) {

    suspend fun getLatestNews(): List<Story> {
        val storyList = mutableListOf<Story>()

        //fetch the latest idList from the HN api
        val idList = withContext(Dispatchers.IO) {
            api.getStoryIdList().body()
        }

        //fetch the top 20 results from the api and parse it to the list
        idList
            ?.subList(START_POSITION, CONTENT_SIZE)
            ?.forEach {
                val storyItem = api.getStory(it.toString()).body()
                storyList.add(storyItem!!)
                Log.e(" repository//fetching: ", "$storyItem")
            }
        return storyList
    }

    companion object {
        const val START_POSITION = 0
        const val CONTENT_SIZE = 20
    }
}
