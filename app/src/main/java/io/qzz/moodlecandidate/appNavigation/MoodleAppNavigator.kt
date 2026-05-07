package io.qzz.moodlecandidate.appNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.qzz.moodlecandidate.feature.courseDetails.CourseDetailsRoute
import io.qzz.moodlecandidate.feature.courses.CoursesRoute
import io.qzz.moodlecandidate.feature.courses.model.Course
import io.qzz.moodlecandidate.feature.grades.GradesRoute
import io.qzz.moodlecandidate.shared.constants.NavigationConstants

@Composable
fun MoodleCandidateApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MoodleAppRoutes.courses
    ) {
        composable(MoodleAppRoutes.courses) {
            CoursesRoute(
                onOpenCourse = { course ->
                    navController.navigate(MoodleAppRoutes.details(course.id, course.title))
                }
            )
        }

        composable(
            route = MoodleAppRoutes.details,
            arguments = listOf(
                navArgument(NavigationConstants.courseIdArg) { type = NavType.IntType },
                navArgument(NavigationConstants.courseTitleArg) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt(NavigationConstants.courseIdArg) ?: return@composable
            val courseTitle = backStackEntry.arguments?.getString(NavigationConstants.courseTitleArg).orEmpty()
            val course = Course(
                id = courseId,
                title = courseTitle,
                progress = null,
                imageUrl = null
            )

            CourseDetailsRoute(
                course = course,
                onBack = { navController.popBackStack() },
                onOpenGrades = {
                    navController.navigate(MoodleAppRoutes.grades(course.id, course.title))
                }
            )
        }

        composable(
            route = MoodleAppRoutes.grades,
            arguments = listOf(
                navArgument(NavigationConstants.courseIdArg) { type = NavType.IntType },
                navArgument(NavigationConstants.courseTitleArg) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt(NavigationConstants.courseIdArg) ?: return@composable
            val courseTitle = backStackEntry.arguments?.getString(NavigationConstants.courseTitleArg).orEmpty()
            val course = Course(
                id = courseId,
                title = courseTitle,
                progress = null,
                imageUrl = null
            )

            GradesRoute(
                course = course,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

