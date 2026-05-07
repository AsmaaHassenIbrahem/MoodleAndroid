package io.qzz.moodlecandidate.feature.courseDetails.data

import io.qzz.moodlecandidate.feature.courseDetails.model.CourseSection
import io.qzz.moodlecandidate.shared.constants.AppText
import io.qzz.moodlecandidate.shared.network.HtmlSanitizer

data class MoodleSectionDto(
    val id: Int,
    val name: String?,
    val summary: String?
) {
    fun toModel(): CourseSection {
        val fallback = HtmlSanitizer.toPlainText(summary.orEmpty()).trim()
        return CourseSection(
            id = id,
            title = (name?.takeIf { it.isNotBlank() } ?: fallback).ifBlank { AppText.untitledSection }
        )
    }
}
