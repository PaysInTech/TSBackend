package app.paysintech.di.module

import app.paysintech.api.authentication.Authenticator
import app.paysintech.api.authentication.FirebaseAuthenticator
import app.paysintech.http.firebase.authentication.AuthenticationManager
import app.paysintech.http.firebase.authentication.FirebaseAuthenticationManager
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
