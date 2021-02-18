package tk.zedlabs.statetest.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.zedlabs.statetest.data.JsonApi
import tk.zedlabs.statetest.model.Story
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: JsonApi,
) {

    suspend fun getRemoteNews(): List<Story> {
        val storyList = mutableListOf<Story>()

        //fetch the latest idList from the HN api
        val idList = withContext(Dispatchers.IO) {
            api.getStoryIdList().body()
        }

        //fetch the top 20 results from the api and parse it to the list
        idList
            ?.subList(MainRepository.START_POSITION, MainRepository.CONTENT_SIZE)
            ?.forEach {
                val storyItem = api.getStory(it.toString()).body()
                storyList.add(storyItem!!)
                Log.e(" repository//fetching: ", "${storyItem.title}")
            }
        return storyList
    }


}