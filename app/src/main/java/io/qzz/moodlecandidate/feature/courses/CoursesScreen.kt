package io.qzz.moodlecandidate.feature.courses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.qzz.moodlecandidate.feature.courses.model.Course
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.ui.ErrorState
import io.qzz.moodlecandidate.shared.ui.LoadingState
import io.qzz.moodlecandidate.shared.ui.UiState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesRoute(
    onOpenCourse: (Course) -> Unit,
    viewModel: CoursesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (state is UiState.Idle) viewModel.load()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(AppText.coursesTitle) }) }
    ) { padding ->
        when (val current = state) {
            UiState.Idle, UiState.Loading -> LoadingState(Modifier.padding(padding))
            is UiState.Error -> ErrorState(
                message = current.message,
                onRetry = viewModel::load,
                modifier = Modifier.padding(padding)
            )
            is UiState.Success -> CoursesScreen(
                courses = current.data,
                onOpenCourse = onOpenCourse,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun CoursesScreen(
    courses: List<Course>,
    onOpenCourse: (Course) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(courses, key = { it.id }) { course ->
            Card(onClick = { onOpenCourse(course) }) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    AsyncImage(
                        model = course.imageUrl,
                        contentDescription = course.title,
                        modifier = Modifier.size(72.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text(course.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        if (course.progress != null) {
                            LinearProgressIndicator(
                                progress = { (course.progress / 100.0).toFloat() },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(AppText.progressComplete(course.progress), style = MaterialTheme.typography.bodySmall)
                        } else {
                            Text(AppText.progressUnavailable, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
