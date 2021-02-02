package tk.zedlabs.statetest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: JsonApi
) {

    val latestNews: Flow<List<Story?>?> = flow {
        while (true){
            //val newList: MutableList<Story> = mutableListOf()
            val newList = api.getStoryIdList().body()
                ?.map { storyId -> api.getStory(storyId.toString()).body() }

//            .forEach { storyId ->
//                newList.add(api.getStory(storyId.toString()))
//            }
            emit(newList)
        }
    }
}