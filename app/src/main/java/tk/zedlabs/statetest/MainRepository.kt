package tk.zedlabs.statetest

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: JsonApi
) {
    suspend fun getLatestNewsList(): Flow<List<Story>> = flow {

        val storyList = mutableListOf<Story>()

        //fetch the latest idList from the HN api
        val idList = withContext(Dispatchers.Default) {
            api.getStoryIdList().body()
        }

        //fetch the top 20 results from the api and parse it to the list
        idList?.subList(1, 20)
            ?.forEach {
                val storyItem = api.getStory(it.toString()).body()
                Log.e("repository//fetching: ", "${storyItem?.title}")
                storyList.add(storyItem!!)

            }
        emit(storyList)
    }

}