package tk.zedlabs.statetest.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tk.zedlabs.statetest.local_db.StoryDao
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val storyDao: StoryDao,
) : ViewModel() {

    val storyList: MutableState<List<Story>> = mutableStateOf(listOf())
    val loading : MutableState<Boolean> = mutableStateOf(false)

    init {
        viewModelScope.launch {
            loading.value = true
            storyList.value = repository.getCachedNews()
            delay(1000)
            loading.value = false
            updateCache()
        }
    }

    private suspend fun updateCache(){
        withContext(Dispatchers.IO){
            val newList = repository.getLatestNews()
            storyDao.deleteAll()

            newList.forEach { story ->
                storyDao.insertAll(story)
            }

            loading.value = true
            storyList.value = repository.getCachedNews()
            delay(1000)
            loading.value = false
        }

    }
}