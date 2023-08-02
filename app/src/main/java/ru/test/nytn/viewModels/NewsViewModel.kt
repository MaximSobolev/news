package ru.test.nytn.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.test.nytn.R
import ru.test.nytn.dto.AppState
import ru.test.nytn.dto.News
import ru.test.nytn.repository.NewsRepository
import ru.test.nytn.utills.ApiError
import ru.test.nytn.utills.AppError
import ru.test.nytn.utills.NetworkError
import ru.test.nytn.utills.UnknownError
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var year : String
    private var month : String

    init {
        val date = LocalDate.now()
        year = date.year.toString()
        month = date.month.value.toString()
    }

    private val _newsList = MutableLiveData<List<News>>()
    val newsList : LiveData<List<News>>
        get() = _newsList

    private val _appState = MutableLiveData<AppState>()
    val appState : LiveData<AppState>
        get() = _appState

    private val _error = MutableLiveData<Int?>()
    val error : LiveData<Int?>
        get() = _error

    fun getNewsList() {
        viewModelScope.launch {
            _appState.postValue(AppState(loading = true))
            try {
                val newsList = repository.getNews(year, month)
                _newsList.postValue(newsList)
                if (newsList.isEmpty()) {
                    _appState.postValue(AppState(emptyList = true))
                } else {
                    _appState.postValue(AppState(idle = true))
                }
            } catch (e : AppError) {
                _appState.postValue(AppState(error = true))
                _error.postValue(manipulateError(e))
            }
        }
    }

    private fun manipulateError(e : AppError) : Int {
        return when(e) {
            is ApiError -> R.string.retry_text
            is NetworkError -> R.string.network_problems
            is UnknownError -> R.string.unknown_problems
        }
    }

    fun errorIsShow() {
        _error.postValue(null)
    }

    fun getNYTLink() : String {
        return repository.getNYTLink()
    }

}