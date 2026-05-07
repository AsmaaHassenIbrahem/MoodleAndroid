package io.qzz.moodlecandidate.feature.courses.data

import io.qzz.moodlecandidate.shared.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesApi {
    @GET(ApiConstants.restEndpoint)
    suspend fun getUserCourses(
        @Query(ApiConstants.tokenParam) token: String,
        @Query(ApiConstants.functionParam) function: String = ApiConstants.getUserCoursesFunction,
        @Query(ApiConstants.formatParam) format: String = ApiConstants.jsonFormat,
        @Query(ApiConstants.userIdParam) userId: Int
    ): List<MoodleCourseDto>
}
