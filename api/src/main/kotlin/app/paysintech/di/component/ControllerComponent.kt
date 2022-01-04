package app.paysintech.di.component

import app.paysintech.api.health.HealthController
import app.paysintech.api.info.InfoController
import app.paysintech.api.salary.SalaryController
import app.paysintech.api.user.UserController
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
