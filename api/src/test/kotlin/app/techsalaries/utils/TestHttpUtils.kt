package app.techsalaries.utils

import app.techsalaries.withTechSalariesTestApp
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.handleRequest

object TestHttpUtils {
    fun get(
        url: String,
        setup: TestApplicationRequest.() -> Unit = {}
    ) = withTechSalariesTestApp { handleRequest(HttpMethod.Get, url, setup) }

    fun post(
        url: String,
        setup: TestApplicationRequest.() -> Unit = {}
    ) = withTechSalariesTestApp { handleRequest(HttpMethod.Post, url, setup) }
}
