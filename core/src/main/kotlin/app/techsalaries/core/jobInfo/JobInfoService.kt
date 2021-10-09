package app.techsalaries.core.jobInfo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobInfoService @Inject constructor(private val repository: JobInfoRepository) {
    suspend fun getAllJobProfiles() = repository.getAllJobProfiles()

    suspend fun getAllTechnologies() = repository.getAllTechnologies()

    suspend fun getAllProgrammingLanguages() = repository.getAllProgrammingLanguages()
}