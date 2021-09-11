package app.techsalaries.di.component

import app.techsalaries.api.sample.SampleController
import dagger.Lazy
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface ControllerComponent {

    @Singleton
    fun sampleController(): Lazy<SampleController>
}
