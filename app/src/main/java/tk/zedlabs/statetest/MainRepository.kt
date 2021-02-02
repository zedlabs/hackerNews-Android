package tk.zedlabs.statetest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {

    private val api = RetrofitService.createService(JsonApi::class.java)


    val latestNews: Flow<List<Story>> = flow {
        while (true){
            //val newList: MutableList<Story> = mutableListOf()
            val newList = api.getStoryIdList()
                .map { storyId -> api.getStory(storyId.toString()) }

//            .forEach { storyId ->
//                newList.add(api.getStory(storyId.toString()))
//            }
            emit(newList)
        }
    }
}