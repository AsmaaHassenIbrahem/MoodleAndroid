package io.qzz.moodlecandidate.shared.constants

object AppText {
    const val coursesTitle = "Courses"
    const val gradesTitle = "Grades"
    const val loadingCourses = "Loading courses..."
    const val loadingSections = "Loading sections..."
    const val loadingGrades = "Loading grades..."
    const val usingFallbackToken = "Using fallback token"
    const val viewGrades = "View Grades"
    const val progressUnavailable = "Progress unavailable"
    const val backContentDescription = "Back"
    const val somethingWentWrong = "Something went wrong"
    const val retry = "Retry"
    const val untitledCourse = "Untitled Course"
    const val untitledSection = "Untitled Section"
    const val unnamedGrade = "Unnamed Grade"
    const val gradeFallbackId = "grade"
    const val notAvailable = "N/A"
    const val failedToLoadCourses = "Failed to load courses"
    const val failedToLoadSections = "Failed to load sections"
    const val failedToLoadGrades = "Failed to load grades"

    fun progressComplete(value: Double): String = "${value.toInt()}% complete"
}

object ApiConstants {
    const val restEndpoint = "webservice/rest/server.php"
    const val tokenParam = "wstoken"
    const val functionParam = "wsfunction"
    const val formatParam = "moodlewsrestformat"
    const val jsonFormat = "json"
    const val userIdParam = "userid"
    const val courseIdParam = "courseid"
    const val tokenQueryKey = "token"
    const val querySeparator = "?"
    const val queryAppendSeparator = "&"

    const val getUserCoursesFunction = "core_enrol_get_users_courses"
    const val getCourseContentsFunction = "core_course_get_contents"
    const val getGradesFunction = "gradereport_user_get_grade_items"
}

object NavigationConstants {
    const val coursesRoute = "courses"
    const val detailsPattern = "details/{courseId}/{courseTitle}"
    const val gradesPattern = "grades/{courseId}/{courseTitle}"
    const val detailsPrefix = "details"
    const val gradesPrefix = "grades"
    const val courseIdArg = "courseId"
    const val courseTitleArg = "courseTitle"
}

