package app.techsalaries.di.module

import app.techsalaries.core.jobInfo.JobInfoRepository
import app.techsalaries.db.jobInfo.JobInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun jobInfoRepository(repo: JobInfoRepositoryImpl): JobInfoRepository
}
