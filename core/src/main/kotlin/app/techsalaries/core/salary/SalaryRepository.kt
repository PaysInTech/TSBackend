package app.techsalaries.core.salary

import app.techsalaries.core.salary.model.AddSalary

interface SalaryRepository {
    suspend fun addSalary(salary: AddSalary): Long
}
