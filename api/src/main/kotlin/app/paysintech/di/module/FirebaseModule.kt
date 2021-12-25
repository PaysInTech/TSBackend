package app.paysintech.di.module

import app.paysintech.firebase.FirebaseAdminInitializer
import app.paysintech.firebase.FirebaseInitializer
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
