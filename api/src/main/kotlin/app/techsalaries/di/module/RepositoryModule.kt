package app.techsalaries.di.module

import app.techsalaries.core.jobInfo.JobInfoRepository
import app.techsalaries.core.salary.SalaryRepository
import app.techsalaries.db.jobInfo.JobInfoRepositoryImpl
import app.techsalaries.db.salary.SalaryRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun jobInfoRepository(repo: JobInfoRepositoryImpl): JobInfoRepository

    @Singleton
    @Binds
    fun salaryRepository(repo: SalaryRepositoryImpl): SalaryRepository
}
