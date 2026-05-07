package io.qzz.moodlecandidate.feature.courseDetails.data

import io.qzz.moodlecandidate.feature.courseDetails.model.CourseSection

class CourseDetailsRepository(
    private val api: CourseDetailsApi
) {
    suspend fun getSections(token: String, courseId: Int): List<CourseSection> {
        return api.getCourseContents(token = token, courseId = courseId).map { it.toModel() }
    }
}
