package io.qzz.moodlecandidate.feature.grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.qzz.moodlecandidate.feature.grades.data.GradesRepository
import io.qzz.moodlecandidate.feature.grades.model.GradeItem
import io.qzz.moodlecandidate.shared.config.Config
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GradesViewModel(
    private val repository: GradesRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<GradeItem>>>(UiState.Idle)
    val state: StateFlow<UiState<List<GradeItem>>> = _state.asStateFlow()

    fun load(courseId: Int) {
        _state.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                repository.getGrades(Config.DEMO_TOKEN, courseId, Config.DEMO_USER_ID)
            }.onSuccess {
                _state.value = UiState.Success(it)
            }.onFailure {
                _state.value = UiState.Error(it.message ?: AppText.failedToLoadGrades)
            }
        }
    }
}
