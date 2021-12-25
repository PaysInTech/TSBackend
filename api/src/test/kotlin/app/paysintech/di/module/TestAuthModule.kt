package app.paysintech.di.module

import app.paysintech.api.authentication.Authenticator
import app.paysintech.api.authentication.FakeAuthenticationManager
import app.paysintech.api.authentication.FakeAuthenticator
import app.paysintech.http.firebase.authentication.AuthenticationManager
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
