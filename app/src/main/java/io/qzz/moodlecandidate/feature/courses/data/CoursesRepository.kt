package io.qzz.moodlecandidate.feature.courses.data

import io.qzz.moodlecandidate.feature.courses.model.Course

class CoursesRepository(
    private val api: CoursesApi
) {
    suspend fun getCourses(token: String, userId: Int): List<Course> {
        return api.getUserCourses(token = token, userId = userId).map { it.toModel(token) }
    }
}
