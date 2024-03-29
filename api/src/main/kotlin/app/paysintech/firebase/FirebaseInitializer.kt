package app.paysintech.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream
import javax.inject.Inject

interface FirebaseInitializer {
    val firebaseApp: FirebaseApp?
}

class FirebaseAdminInitializer @Inject constructor() : FirebaseInitializer {
    override var firebaseApp: FirebaseApp? = null

    init {
        // The file `firebase-adminsdk-config.json` should be placed at the root directory in main `resources`
        // directory. Also, make sure that you not have MISTAKENLY CHECKOUT THIS FILE IN VCS. IT'S HIGHLY CONFIDENTIAL.
        synchronized(this) {
            val serviceAccountConfig = javaClass.classLoader.getResource("firebase-adminsdk-config.json")!!.file!!

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream(serviceAccountConfig)))
                .build()

            if (firebaseApp == null) {
                firebaseApp = FirebaseApp.initializeApp(options)
            }
        }
    }
}
