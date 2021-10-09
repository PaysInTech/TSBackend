package app.techsalaries.di.component

import app.techsalaries.api.info.InfoController
import dagger.Lazy
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface ControllerComponent {

    @Singleton
    fun infoController(): Lazy<InfoController>
}
