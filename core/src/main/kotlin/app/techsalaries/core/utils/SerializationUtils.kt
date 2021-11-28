package app.techsalaries.core.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Converts any model to String JSON representation
 */
inline fun <reified T> T.json(): String = Json.encodeToString(this)

/**
 * Converts any String JSON to [T] model
 */
inline fun <reified T> fromJson(json: String): T = Json.decodeFromString(json)
