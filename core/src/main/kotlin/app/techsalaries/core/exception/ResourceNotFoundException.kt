package app.techsalaries.core.exception

class ResourceNotFoundException(message: String) : RuntimeException(message)

fun errorResourceNotFound(message: String): Nothing = throw ResourceNotFoundException(message)