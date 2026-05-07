package io.qzz.moodlecandidate.feature.grades.data

import io.qzz.moodlecandidate.feature.grades.model.GradeItem
import io.qzz.moodlecandidate.shared.constants.AppText

data class MoodleGradesResponseDto(
    val usergrades: List<MoodleUserGradeDto>
)

data class MoodleUserGradeDto(
    val gradeitems: List<MoodleGradeItemDto>
)

data class MoodleGradeItemDto(
    val itemname: String?,
    val gradeformatted: String?,
    val percentageformatted: String?
) {
    fun toModel(index: Int): GradeItem {
        return GradeItem(
            id = "$index-${itemname ?: AppText.gradeFallbackId}",
            name = itemname?.ifBlank { AppText.unnamedGrade } ?: AppText.unnamedGrade,
            grade = gradeformatted?.ifBlank { AppText.notAvailable } ?: AppText.notAvailable,
            percentage = percentageformatted
        )
    }
}
