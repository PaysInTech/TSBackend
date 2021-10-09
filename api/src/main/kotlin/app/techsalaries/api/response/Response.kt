package app.techsalaries.api.response

import kotlinx.serialization.Serializable

/**
 * Common base response model
 */
@Serializable
abstract class BaseResponse(val isSuccess: Boolean)
