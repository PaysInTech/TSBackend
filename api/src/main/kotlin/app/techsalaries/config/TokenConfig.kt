package app.techsalaries.config

class TokenConfig(
    val audience: String,
    val secret: String,
    val issuer: String,
    val realm: String
)
