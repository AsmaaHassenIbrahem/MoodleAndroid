package io.qzz.moodlecandidate.feature.grades

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.qzz.moodlecandidate.feature.courses.model.Course
import io.qzz.moodlecandidate.feature.grades.model.GradeItem
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.ui.ErrorState
import io.qzz.moodlecandidate.shared.ui.LoadingState
import io.qzz.moodlecandidate.shared.ui.UiState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesRoute(
    course: Course,
    onBack: () -> Unit,
    viewModel: GradesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(course.id) {
        viewModel.load(course.id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppText.gradesTitle) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = AppText.backContentDescription
                        )
                    }
                }
            )
        }
    ) { padding ->
        when (val current = state) {
            UiState.Idle, UiState.Loading -> LoadingState(Modifier.padding(padding))
            is UiState.Error -> ErrorState(
                message = current.message,
                onRetry = { viewModel.load(course.id) },
                modifier = Modifier.padding(padding)
            )
            is UiState.Success -> GradesScreen(
                items = current.data,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun GradesScreen(
    items: List<GradeItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items, key = { it.id }) { item ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(item.name, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(item.grade, style = MaterialTheme.typography.bodyLarge)
                    item.percentage?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(it, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
