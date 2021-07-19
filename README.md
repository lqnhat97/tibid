# tibid server
## Init
- Run `./gradlew clean build` to build 
- Run `./gradlew bootRun` to run or run in Intellij

## Database
- To use db, go to `mariadb` and run docker compose file inside by the command: `docker compose up`
- DB connection:
  - Server Host: host.docker.internal
  - Port: 9083
  - Root user: root
  - Root password: admin
  - Database name: tibid
  - Username: tibid
  - Password: tibid

## API
- TBD