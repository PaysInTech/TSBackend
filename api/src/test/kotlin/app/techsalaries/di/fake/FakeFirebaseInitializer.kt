package app.techsalaries.di.fake

import app.techsalaries.firebase.FirebaseInitializer
import com.google.firebase.FirebaseApp
import io.mockk.mockk
import javax.inject.Inject

class FakeFirebaseInitializer @Inject constructor() : FirebaseInitializer {
    override val firebaseApp: FirebaseApp = mockk()
}
