package app.paysintech.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import javax.inject.Inject

interface FirebaseInitializer {
    val firebaseApp: FirebaseApp?
}

class FirebaseAdminInitializer @Inject constructor() : FirebaseInitializer {
    override var firebaseApp: FirebaseApp? = null

    init {
        synchronized(this) {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build()

            if (firebaseApp == null) {
                firebaseApp = FirebaseApp.initializeApp(options)
            }
        }
    }
}
