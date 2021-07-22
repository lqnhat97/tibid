# tibid server
## Init
- Run `./gradlew clean build` to build 
- Run `./gradlew bootRun` to run or run in Intellij

## Database
- To use db, go to `mariadb` folder and run docker compose file inside by the command: `docker compose up -d`
- DB connection:
  - Server Host: host.docker.internal (or localhost)
  - Port: 9083
  - Root user: root
  - Root password: admin
  - Database name: tibid
  - Username: tibid
  - Password: tibid

## Kafka
- Use command `docker compose up -d` at root folder

## API
**Note**: Date time have to parse to long value
- Import postman collection at `postman_collection` folder