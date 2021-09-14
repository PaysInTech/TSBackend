rootProject.name = "techsalaries-api"

include("core")

include("port:db")
findProject(":port:db")?.name = "db"

include("api")
include("migration")
