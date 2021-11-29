package app.techsalaries.di.module

import app.techsalaries.firebase.FirebaseAdminInitializer
import app.techsalaries.firebase.FirebaseInitializer
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FirebaseModule {
    @Provides
    fun firebaseAuth(firebaseInitializer: FirebaseInitializer): FirebaseAuth {
        return FirebaseAuth.getInstance(firebaseInitializer.firebaseApp)
    }

    @Provides
    @Singleton
    fun firebaseInitializer(): FirebaseInitializer = FirebaseAdminInitializer()
}
