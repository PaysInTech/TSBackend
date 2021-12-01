package app.techsalaries.api.salary.fixtures

import app.techsalaries.api.salary.model.AddSalaryDetailRequest

fun addSalaryDetailRequest() = AddSalaryDetailRequest(
    company = "Example Technologies",
    designation = "Software Engineer",
    profileId = 1,
    contributionLevel = 1,
    additionalBenefits = AddSalaryDetailRequest.AdditionalBenefitDetails(
        benefits = listOf(AddSalaryDetailRequest.AdditionalBenefitDetails.Benefit(type = "Reimbursement", amount = 500.0))
    ),
    salary = AddSalaryDetailRequest.SalaryDetails(100000.0, 8000.0, 5000.0),
    level = "L1",
    team = "Development",
    joiningDate = "2021-11-11",
    lastWorkingDate = "",
    isPromoted = false,
    workType = "FULL_REMOTE",
    baseLocation = AddSalaryDetailRequest.BaseLocation("Maharashtra", "Mumbai"),
    availableInIndia = true,
    technologies = listOf(1, 2, 3),
    programmingLanguagesUsed = listOf(2, 3, 4)
)
