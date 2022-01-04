package app.paysintech.core.utils

import java.util.Currency

fun getCurrency(currencyCode: String): Currency? {
    return Currency.getAvailableCurrencies().find { it.currencyCode == currencyCode }
}
