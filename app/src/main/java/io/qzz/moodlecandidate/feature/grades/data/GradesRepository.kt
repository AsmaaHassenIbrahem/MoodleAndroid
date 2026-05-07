package io.qzz.moodlecandidate.feature.grades.data

import io.qzz.moodlecandidate.feature.grades.model.GradeItem

class GradesRepository(
    private val api: GradesApi
) {
    suspend fun getGrades(token: String, courseId: Int, userId: Int): List<GradeItem> {
        return api.getGrades(token = token, courseId = courseId, userId = userId)
            .usergrades
            .firstOrNull()
            ?.gradeitems
            ?.mapIndexed { index, item -> item.toModel(index) }
            .orEmpty()
    }
}
