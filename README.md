# tibid server
## Init
- Run `./gradlew clean build` to build 
- Run `./gradlew bootRun` to run or run in Intellij

## Open h2 db:
- Go to `{domain}:{port}/h2` eg: http://localhost:8080/h2
- Set schema at: `resource/schema.sql`
- Init default data at: `resource/data.sql`
