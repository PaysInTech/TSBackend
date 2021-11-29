package app.techsalaries.di.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
object FirebaseModule {
    @Provides
    fun firebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}