package tk.zedlabs.statetest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    val storyListViewState: LiveData<StoryListViewState>
        get() = _storyListViewState

    private var _storyListViewState = MutableLiveData<StoryListViewState>()

    init {
        _storyListViewState.value =
            StoryListViewState(
                isComplete = false,
                error = null,
                stories = null
            )
    }
    fun loadInitialDetails(){

        viewModelScope.launch {
            async { loadStories() }.await()
            _storyListViewState.value = _storyListViewState.value?.copy(isComplete = true)
        }
    }

    private suspend fun loadStories() {
        repository.getLatestNewsList().collect { storiesList ->
            _storyListViewState.value = _storyListViewState.value?.copy(stories = storiesList)
        }
    }


}