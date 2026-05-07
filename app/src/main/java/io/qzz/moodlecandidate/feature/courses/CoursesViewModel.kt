package io.qzz.moodlecandidate.feature.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.qzz.moodlecandidate.feature.courses.data.CoursesRepository
import io.qzz.moodlecandidate.feature.courses.model.Course
import io.qzz.moodlecandidate.shared.config.Config
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Course>>>(UiState.Idle)
    val state: StateFlow<UiState<List<Course>>> = _state.asStateFlow()

    fun load() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                repository.getCourses(Config.DEMO_TOKEN, Config.DEMO_USER_ID)
            }.onSuccess {
                _state.value = UiState.Success(it)
            }.onFailure {
                _state.value = UiState.Error(it.message ?: AppText.failedToLoadCourses)
            }
        }
    }
}
