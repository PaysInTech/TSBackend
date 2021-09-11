package app.techsalaries.di.module

import app.techsalaries.core.sample.SampleRepository
import app.techsalaries.db.sample.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun sampleRepository(sampleRepositoryImpl: SampleRepositoryImpl): SampleRepository
}