package app.paysintech.di.module

import app.paysintech.di.fake.FakeFirebaseInitializer
import app.paysintech.firebase.FirebaseInitializer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestFirebaseModule {
    @Provides
    @Singleton
    fun firebaseInitializer(): FirebaseInitializer = FakeFirebaseInitializer()
}
