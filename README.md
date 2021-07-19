# tibid server
## Init
- Run `./gradlew clean build` to build 
- Run `./gradlew bootRun` to run or run in Intellij

## Database
- To use db, go to `mariadb` and run docker compose file inside by the command: `docker compose up -d`
- DB connection:
  - Server Host: host.docker.internal
  - Port: 9083
  - Root user: root
  - Root password: admin
  - Database name: tibid
  - Username: tibid
  - Password: tibid

## API
**Note**: Date time have to parse to long value
- POST `/createOrder`
  - request body
````json
{
    "userId": "1",
    "productId": "123",
    "startPrice": "1000",
    "priceStep":"10",
    "status":"1",
    "bidStartTime":"123123",
    "bidEndTime":"14234234",
    "type":"NEW",
    "bidQuantity":"2"
}
````
- POST `/createTicket`
  - request body
````json
{
  "userId": "1",
  "bidOrderId": "1",
  "price": "100000",
  "status":"10"
}
````
- GET `/searchOrders`
  - response body
```json
[
  {
    "id": 1,
    "updatedDate": 0,
    "userId": 1,
    "productId": 123,
    "startPrice": 1000.0,
    "priceStep": 10.0,
    "status": 1,
    "bidStartTime": 123123,
    "bidEndTime": 14234234,
    "type": "NEW",
    "bidQuantity": 2,
    "createdAt": 1626702518929,
    "modifiedAt": 1626702518929
  }
]
```