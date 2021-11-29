package app.techsalaries.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

object FirebaseInitializer {
    var isInitialized = false
        private set

    operator fun invoke() {
        // The file `firebase-adminsdk-config.json` should be placed at the root directory in main `resources`
        // directory. Also, make sure that you not have MISTAKENLY CHECKOUT THIS FILE IN VCS. IT'S HIGHLY CONFIDENTIAL.
        val serviceAccountConfig = javaClass.classLoader.getResource("firebase-adminsdk-config.json")!!.file!!

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(FileInputStream(serviceAccountConfig)))
            .build()

        if (isInitialized) return

        FirebaseApp.initializeApp(options).name
//        isInitialized = true
    }
}