package app.techsalaries.di.module

import app.techsalaries.http.firebase.authentication.client.FirebaseAuthApiClient
import app.techsalaries.http.firebase.authentication.client.FirebaseAuthApiClientImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface HttpClientModule {

    @Singleton
    @Binds
    fun firebaseAuthApiClient(client: FirebaseAuthApiClientImpl): FirebaseAuthApiClient
}
