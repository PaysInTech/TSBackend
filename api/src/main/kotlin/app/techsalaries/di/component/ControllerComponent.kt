package app.techsalaries.di.component

import app.techsalaries.api.health.HealthController
import app.techsalaries.api.info.InfoController
import app.techsalaries.api.salary.SalaryController
import app.techsalaries.api.user.UserController
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface ControllerComponent {

    @Singleton
    fun infoController(): InfoController

    @Singleton
    fun healthController(): HealthController

    @Singleton
    fun salaryController(): SalaryController

    @Singleton
    fun userController(): UserController
}
