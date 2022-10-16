# User Management System 

### Technology Stack
* Java JDK 17
* Spring Boot Version 2.7.4
* PostgreSQL Database Server

### Getting Started the Services:

* Starting the PostgreSQL Service
```shell
docker-compose -f docker-compose-postgresq.yaml up -d
```
* Deployment the service by using the deploy.sh script
```shell
./deploy.sh
```

* Access To the Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

### Testing the API

* **/api/v1/appusers**: To create a new user
```json
{
  "username": "PENHCHET",
  "lastName": "DARA",
  "firstName": "PENHCHET",
  "gender": "MALE",
  "dateOfBirth": "1992-03-01",
  "countryCode": "FR",
  "email": "darapenhchet@gmail.com",
  "password": "123456",
  "confirmPassword": "123456",
  "enabled": true
}
```

* **/api/v1/appusers/{userId}**: To get user details
```json
{
  "code": "0000",
  "message": "OK",
  "data": {
    "id": 1,
    "country": {
      "name": "France",
      "alpha3Code": "FRA",
      "alpha2Code": "FR"
    },
    "enabled": true,
    "username": "ADMIN",
    "email": "admin@gmail.com",
    "lastname": "ADMIN",
    "firstname": "ADMIN",
    "firstTimeLoginRemaining": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "deleted": false
  }
}
```
* **/api/v1/appusers**: To list all users
```json
{
  "code": "0000",
  "message": "OK",
  "data": [
    {
      "id": 8,
      "country": {
        "name": "France",
        "alpha3Code": "FRA",
        "alpha2Code": "FR"
      },
      "enabled": true,
      "username": "PENHCHET1",
      "email": "darapenhchet@gmail.com",
      "lastname": "DARA",
      "firstname": "PENHCHET",
      "firstTimeLoginRemaining": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true,
      "accountNonExpired": true,
      "deleted": false
    },
    {
      "id": 9,
      "country": {
        "name": "France",
        "alpha3Code": "FRA",
        "alpha2Code": "FR"
      },
      "enabled": true,
      "username": "PENHCHET12",
      "email": "darapenhchet@gmail.com",
      "lastname": "DARA",
      "firstname": "PENHCHET",
      "firstTimeLoginRemaining": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true,
      "accountNonExpired": true,
      "deleted": false
    },
    {
      "id": 1,
      "country": {
        "name": "France",
        "alpha3Code": "FRA",
        "alpha2Code": "FR"
      },
      "enabled": true,
      "username": "ADMIN",
      "email": "admin@gmail.com",
      "lastname": "ADMIN",
      "firstname": "ADMIN",
      "firstTimeLoginRemaining": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true,
      "accountNonExpired": true,
      "deleted": false
    }
  ]
}
```
