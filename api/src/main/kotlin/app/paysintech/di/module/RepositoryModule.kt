package app.paysintech.di.module

import app.paysintech.core.jobInfo.JobInfoRepository
import app.paysintech.core.salary.SalaryRepository
import app.paysintech.core.user.UserRepository
import app.paysintech.db.jobInfo.JobInfoRepositoryImpl
import app.paysintech.db.salary.SalaryRepositoryImpl
import app.paysintech.db.user.UserRepositoryImpl
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

    @Singleton
    @Binds
    fun userRepository(repo: UserRepositoryImpl): UserRepository
}
