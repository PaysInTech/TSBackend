package app.techsalaries.core.utils

import java.util.Currency

fun getCurrency(currencyCode: String): Currency? {
    return Currency.getAvailableCurrencies().find { it.currencyCode == currencyCode }
}
