package app.paysintech.utils

import app.paysintech.withPaysInTechTestApp
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.handleRequest

object TestHttpUtils {
    fun get(
        url: String,
        before: TestApplicationEngine.() -> Unit = {},
        after: TestApplicationEngine.() -> Unit = {},
        setup: TestApplicationRequest.() -> Unit = {}
    ) = handleRequest(HttpMethod.Get, url, before, after, setup)

    fun post(
        url: String,
        before: TestApplicationEngine.() -> Unit = {},
        after: TestApplicationEngine.() -> Unit = {},
        setup: TestApplicationRequest.() -> Unit = {}
    ) = handleRequest(HttpMethod.Post, url, before, after, setup)

    fun handleRequest(
        method: HttpMethod,
        url: String,
        before: TestApplicationEngine.() -> Unit = {},
        after: TestApplicationEngine.() -> Unit = {},
        setup: TestApplicationRequest.() -> Unit = {}
    ) = withPaysInTechTestApp {
        before()
        val call = handleRequest(method, url, setup)
        after()
        return@withPaysInTechTestApp call
    }
}
