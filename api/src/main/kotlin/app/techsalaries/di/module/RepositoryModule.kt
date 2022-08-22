package app.techsalaries.di.module

import app.techsalaries.core.sample.SampleRepository
import app.techsalaries.core.user.UserRepository
import app.techsalaries.db.sample.SampleRepositoryImpl
import app.techsalaries.db.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun sampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository

    @Singleton
    @Binds
    fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
