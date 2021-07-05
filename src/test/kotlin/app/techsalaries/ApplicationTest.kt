package app.techsalaries

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.auth.*
import io.ktor.util.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import app.techsalaries.plugins.*
import io.ktor.server.testing.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }
}