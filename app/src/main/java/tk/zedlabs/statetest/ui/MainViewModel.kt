package tk.zedlabs.statetest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tk.zedlabs.statetest.repository.MainRepository

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
        loadInitialDetails()
    }

    fun loadInitialDetails() {

        viewModelScope.launch {
            async { loadStories()}.await()
            _storyListViewState.value = _storyListViewState.value?.copy(isComplete = true)
        }

    }

    private suspend fun loadStories() {
        repository.getLatestNewsList()
            .catch { exception ->
                _storyListViewState.value =
                    _storyListViewState.value?.copy(error = Error(exception))
                exception.printStackTrace()
            }
            .collect { storiesList ->
                _storyListViewState.value = _storyListViewState.value?.copy(stories = storiesList)
            }
    }


}