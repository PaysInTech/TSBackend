package app.techsalaries.di.module

import app.techsalaries.api.authentication.Authenticator
import app.techsalaries.api.authentication.FirebaseAuthenticator
import app.techsalaries.http.firebase.authentication.AuthenticationManager
import app.techsalaries.http.firebase.authentication.FirebaseAuthenticationManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AuthModule {
    @Singleton
    @Binds
    fun authenticator(authenticator: FirebaseAuthenticator): Authenticator

    @Singleton
    @Binds
    fun authManager(manager: FirebaseAuthenticationManager): AuthenticationManager
}
