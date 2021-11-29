package app.techsalaries.di.module

import app.techsalaries.di.fake.FakeFirebaseInitializer
import app.techsalaries.firebase.FirebaseInitializer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestFirebaseModule {
    @Provides
    @Singleton
    fun firebaseInitializer(): FirebaseInitializer = FakeFirebaseInitializer()
}
