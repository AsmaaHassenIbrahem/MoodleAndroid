package io.qzz.moodlecandidate.feature.courseDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.qzz.moodlecandidate.feature.courseDetails.data.CourseDetailsRepository
import io.qzz.moodlecandidate.feature.courseDetails.model.CourseSection
import io.qzz.moodlecandidate.shared.config.Config
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseDetailsViewModel(
    private val repository: CourseDetailsRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<CourseSection>>>(UiState.Idle)
    val state: StateFlow<UiState<List<CourseSection>>> = _state.asStateFlow()

    fun load(courseId: Int) {
        _state.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                repository.getSections(Config.DEMO_TOKEN, courseId)
            }.onSuccess {
                _state.value = UiState.Success(it)
            }.onFailure {
                _state.value = UiState.Error(it.message ?: AppText.failedToLoadSections)
            }
        }
    }
}
