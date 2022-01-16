package app.techsalaries.di.module

import app.techsalaries.config.TokenConfig
import app.techsalaries.service.TokenManager
import dagger.Module
import dagger.Provides

@Module
object ServiceModule {

    @Provides
    fun tokenManager(tokenConfig: TokenConfig): TokenManager = TokenManager(tokenConfig)
}
