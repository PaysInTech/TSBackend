package app.techsalaries.di.component

import app.techsalaries.api.health.HealthController
import app.techsalaries.api.info.InfoController
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface ControllerComponent {

    @Singleton
    fun infoController(): InfoController

    @Singleton
    fun healthController(): HealthController
}
