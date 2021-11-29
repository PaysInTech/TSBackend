package app.techsalaries.core.salary.util

import app.techsalaries.core.salary.model.AddSalaryDetail

class CoinsCounter(private val addSalary: AddSalaryDetail, private val authentication: Boolean = false) {
    /**
     * Returns the count of eligible coins earned on the basis of provided details
     */
    val count: Int
        get() {
            val counter = Counter()

            if (authentication) {
                counter.incrementBy(Coins.AUTHENTICATION)
            }

            // Consider coins for required fields earlier
            counter.incrementBy(
                Coins.CTC +
                    Coins.IN_HAND_SALARY +
                    Coins.COMPANY_NAME +
                    Coins.DESIGNATION +
                    Coins.JOB_PROFILE +
                    Coins.JOINING_DATE +
                    Coins.LAST_WORKING_DATE +
                    Coins.WORK_TYPE +
                    Coins.IN_INDIA
            )

            with(addSalary) {
                counter.incrementBy(Coins.PER_BENEFIT * (additionalBenefits?.benefits?.size ?: 0))
                contributionLevel?.let { counter.incrementBy(Coins.CONTRIBUTION_LEVEL) }
                team?.let { counter.incrementBy(Coins.TEAM) }
                isPromoted?.let { counter.incrementBy(Coins.PROMOTION) }
                baseLocation?.let {
                    it.city?.let { counter.incrementBy(Coins.LOCATION_CITY) }
                    it.state?.let { counter.incrementBy(Coins.LOCATION_STATE) }
                }
                technologies?.let { counter.incrementBy(Coins.TECHNOLOGY) }
            }

            return counter.count
        }

    /**
     * Utility for positive counting
     */
    class Counter(initialCount: Int = 0) {
        private var _count = initialCount

        val count get() = _count

        fun incrementBy(count: Int) { _count += count }
    }

    /**
     * Coin value per detail. If a value is present in the data, then corresponding amount will be added in the
     * account of coins
     */
    object Coins {
        const val AUTHENTICATION = 1000
        const val CTC = 500
        const val IN_HAND_SALARY = 500
        const val PER_BENEFIT = 200
        const val COMPANY_NAME = 1000
        const val DESIGNATION = 500
        const val JOB_PROFILE = 500
        const val CONTRIBUTION_LEVEL = 500
        const val TEAM = 500
        const val JOINING_DATE = 500
        const val LAST_WORKING_DATE = 500
        const val PROMOTION = 1000
        const val WORK_TYPE = 100
        const val LOCATION_STATE = 100
        const val LOCATION_CITY = 100
        const val IN_INDIA = 100
        const val TECHNOLOGY = 500
    }
}