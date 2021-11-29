rootProject.name = "techsalaries-api"

include("core")

include("port:db")
findProject(":port:db")?.name = "db"

include("port:http")
findProject(":port:http")?.name = "http"

include("api")
include("migration")