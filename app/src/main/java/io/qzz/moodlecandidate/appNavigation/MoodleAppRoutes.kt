package io.qzz.moodlecandidate.appNavigation

import android.net.Uri
import io.qzz.moodlecandidate.shared.constants.NavigationConstants

object MoodleAppRoutes {
    const val courses = NavigationConstants.coursesRoute
    const val details = NavigationConstants.detailsPattern
    const val grades = NavigationConstants.gradesPattern

    fun details(courseId: Int, courseTitle: String): String {
        return "${NavigationConstants.detailsPrefix}/$courseId/${Uri.encode(courseTitle)}"
    }

    fun grades(courseId: Int, courseTitle: String): String {
        return "${NavigationConstants.gradesPrefix}/$courseId/${Uri.encode(courseTitle)}"
    }
}

