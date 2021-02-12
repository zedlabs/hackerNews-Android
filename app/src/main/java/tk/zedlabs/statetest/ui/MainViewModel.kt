package tk.zedlabs.statetest.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tk.zedlabs.statetest.model.Story
import tk.zedlabs.statetest.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    val storyList: MutableState<List<Story>> = mutableStateOf(listOf())
    val loading : MutableState<Boolean> = mutableStateOf(false)
    init {
        viewModelScope.launch {
            loading.value = true
            storyList.value = repository.getLatestNews()
            loading.value = false
        }
    }
}