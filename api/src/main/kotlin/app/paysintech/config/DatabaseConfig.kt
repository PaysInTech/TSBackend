package app.paysintech.config

class DatabaseConfig(
    val host: String,
    val port: String,
    val name: String,
    val username: String,
    val password: String
) {
    val url = "jdbc:postgresql://$host:$port/$name"
}
