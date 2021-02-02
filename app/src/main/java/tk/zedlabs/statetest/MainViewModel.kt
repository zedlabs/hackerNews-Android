package tk.zedlabs.statetest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _storyList = repository
        .latestNews
        .asLiveData(viewModelScope.coroutineContext)

    val storyList: LiveData<List<Story?>?>
        get() = _storyList

}