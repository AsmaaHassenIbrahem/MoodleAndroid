package io.qzz.moodlecandidate.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.qzz.moodlecandidate.feature.courseDetails.CourseDetailsViewModel
import io.qzz.moodlecandidate.feature.courseDetails.data.CourseDetailsApi
import io.qzz.moodlecandidate.feature.courseDetails.data.CourseDetailsRepository
import io.qzz.moodlecandidate.feature.courses.CoursesViewModel
import io.qzz.moodlecandidate.feature.courses.data.CoursesApi
import io.qzz.moodlecandidate.feature.courses.data.CoursesRepository
import io.qzz.moodlecandidate.feature.grades.GradesViewModel
import io.qzz.moodlecandidate.feature.grades.data.GradesApi
import io.qzz.moodlecandidate.feature.grades.data.GradesRepository
import io.qzz.moodlecandidate.shared.config.Config
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val networkModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<CoursesApi> { get<Retrofit>().create(CoursesApi::class.java) }
    single<CourseDetailsApi> { get<Retrofit>().create(CourseDetailsApi::class.java) }
    single<GradesApi> { get<Retrofit>().create(GradesApi::class.java) }
}

private val coursesModule = module {
    single { CoursesRepository(get()) }
    viewModel { CoursesViewModel(get()) }
}

private val courseDetailsModule = module {
    single { CourseDetailsRepository(get()) }
    viewModel { CourseDetailsViewModel(get()) }
}

private val gradesModule = module {
    single { GradesRepository(get()) }
    viewModel { GradesViewModel(get()) }
}

val appModules = listOf(
    networkModule,
    coursesModule,
    courseDetailsModule,
    gradesModule
)

