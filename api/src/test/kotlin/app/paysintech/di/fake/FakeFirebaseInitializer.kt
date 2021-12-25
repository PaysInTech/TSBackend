package app.paysintech.di.fake

import app.paysintech.firebase.FirebaseInitializer
import com.google.firebase.FirebaseApp
import io.mockk.mockk
import javax.inject.Inject

class FakeFirebaseInitializer @Inject constructor() : FirebaseInitializer {
    override val firebaseApp: FirebaseApp = mockk()
}
