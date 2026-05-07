package io.qzz.moodlecandidate.feature.courses.data

import io.qzz.moodlecandidate.feature.courses.model.Course
import io.qzz.moodlecandidate.shared.constants.ApiConstants
import io.qzz.moodlecandidate.shared.constants.AppText

data class MoodleCourseDto(
    val id: Int,
    val fullname: String?,
    val displayname: String?,
    val progress: Double?,
    val courseimage: String?,
    val overviewfiles: List<MoodleOverviewFileDto>?
) {
    fun toModel(token: String): Course {
        val rawImage = courseimage ?: overviewfiles?.firstOrNull()?.fileurl
        val resolvedImage = rawImage?.appendTokenIfNeeded(token)
        return Course(
            id = id,
            title = fullname ?: displayname ?: AppText.untitledCourse,
            progress = progress,
            imageUrl = resolvedImage
        )
    }
}

data class MoodleOverviewFileDto(
    val fileurl: String?
)

private fun String.appendTokenIfNeeded(token: String): String {
    return if (contains("${ApiConstants.tokenQueryKey}=")) this else {
        val separator = if (contains(ApiConstants.querySeparator)) {
            ApiConstants.queryAppendSeparator
        } else {
            ApiConstants.querySeparator
        }
        this + separator + ApiConstants.tokenQueryKey + "=" + token
    }
}
