package io.qzz.moodlecandidate.feature.courses.model

data class Course(
    val id: Int,
    val title: String,
    val progress: Double?,
    val imageUrl: String?
)

