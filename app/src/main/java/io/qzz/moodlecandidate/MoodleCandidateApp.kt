package io.qzz.moodlecandidate

import android.app.Application
import io.qzz.moodlecandidate.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoodleCandidateApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoodleCandidateApp)
            modules(appModules)
        }
    }
}
