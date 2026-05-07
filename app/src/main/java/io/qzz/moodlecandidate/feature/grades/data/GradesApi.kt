package io.qzz.moodlecandidate.feature.grades.data

import io.qzz.moodlecandidate.shared.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GradesApi {
    @GET(ApiConstants.restEndpoint)
    suspend fun getGrades(
        @Query(ApiConstants.tokenParam) token: String,
        @Query(ApiConstants.functionParam) function: String = ApiConstants.getGradesFunction,
        @Query(ApiConstants.formatParam) format: String = ApiConstants.jsonFormat,
        @Query(ApiConstants.courseIdParam) courseId: Int,
        @Query(ApiConstants.userIdParam) userId: Int
    ): MoodleGradesResponseDto
}
