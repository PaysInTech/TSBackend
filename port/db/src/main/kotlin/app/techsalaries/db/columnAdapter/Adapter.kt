package app.techsalaries.db.columnAdapter

interface Adapter<TYPE, COLUMN_TYPE> {
    fun decode(value: COLUMN_TYPE): TYPE
    fun encode(value: TYPE): COLUMN_TYPE
}
