package tk.zedlabs.statetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
): ViewModel() {

    private val _weatherForecast = repository
        .latestNews
        .asLiveData(viewModelScope.coroutineContext)

    val weatherForecast: LiveData<List<Story>>
        get() = _weatherForecast

}