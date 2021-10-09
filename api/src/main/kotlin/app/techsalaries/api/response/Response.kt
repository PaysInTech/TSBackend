package app.techsalaries.api.response

import kotlinx.serialization.Serializable

@Serializable
abstract class BaseResponse(val isSuccess: Boolean)