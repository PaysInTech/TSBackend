package app.techsalaries.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Parses Date from specified [value] of given [pattern]
 *
 * @return [LocalDate] or null if fails to parse
 */
fun localDateFrom(
    value: String,
    pattern: String = "yyyy-MM-dd"
): LocalDate? = runCatching { LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern)) }.getOrNull()
