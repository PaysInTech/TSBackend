package app.techsalaries.di.module

import app.techsalaries.api.authentication.Authenticator
import app.techsalaries.api.authentication.FakeAuthenticationManager
import app.techsalaries.api.authentication.FakeAuthenticator
import app.techsalaries.http.firebase.authentication.AuthenticationManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface TestAuthModule {
    @Singleton
    @Binds
    fun authenticator(authenticator: FakeAuthenticator): Authenticator

    @Singleton
    @Binds
    fun authManager(manager: FakeAuthenticationManager): AuthenticationManager
}