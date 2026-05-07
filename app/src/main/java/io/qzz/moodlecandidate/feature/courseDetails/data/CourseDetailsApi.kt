package io.qzz.moodlecandidate.feature.courseDetails.data

import io.qzz.moodlecandidate.shared.constants.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseDetailsApi {
    @GET(ApiConstants.restEndpoint)
    suspend fun getCourseContents(
        @Query(ApiConstants.tokenParam) token: String,
        @Query(ApiConstants.functionParam) function: String = ApiConstants.getCourseContentsFunction,
        @Query(ApiConstants.formatParam) format: String = ApiConstants.jsonFormat,
        @Query(ApiConstants.courseIdParam) courseId: Int
    ): List<MoodleSectionDto>
}
