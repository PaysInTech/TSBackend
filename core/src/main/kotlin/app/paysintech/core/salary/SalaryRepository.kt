package app.paysintech.core.salary

import app.paysintech.core.salary.model.AddSalary

interface SalaryRepository {
    suspend fun addSalary(salary: AddSalary): Long
}
