package app.techsalaries.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Converts any model to String JSON representation
 */
val Any.json: String
    get() = Json.encodeToString(this)

/**
 * Converts any String JSON to [T] model
 */
inline fun <reified T> fromJson(json: String): T = Json.decodeFromString(json)
