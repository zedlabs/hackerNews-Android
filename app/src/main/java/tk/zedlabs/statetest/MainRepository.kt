package tk.zedlabs.statetest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {

    private val api = RetrofitService.createService(JsonApi::class.java)


    val latestNews: Flow<MutableList<Story>> = flow {
        val newList: MutableList<Story> = mutableListOf()

        val latestNews = api.getStoryIdList()
            .forEach { storyId ->
                newList.add(api.getStory(storyId.toString()))
            }
        emit(newList)
    }
}