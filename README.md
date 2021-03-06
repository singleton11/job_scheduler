# Job Scheduler


This is example of background task scheduler

## Exposed REST API reference


### Create task

```http request

POST http://localhost:8080/task

HTTP/1.1 202 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 25 Aug 2018 21:01:08 GMT

{
  "guid": "5a05307d-4cdf-4bf5-a68a-153234d11609"
}

Response code: 202; Time: 137ms; Content length: 47 bytes

```

### Obtain task status

```http request

GET http://localhost:8080/task/7cbf7031-3c7e-490c-bd3b-de21fb025c2b

HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 25 Aug 2018 21:03:11 GMT

{
  "timestamp": "2018-08-25T10:19:25.334+0000",
  "status": "finished"
}

Response code: 200; Time: 26ms; Content length: 64 bytes

```

## How to run

* Clone repository: `git clone https://github.com/singleton11/job_scheduler`
* Ensure you have PostgreSQL server which listens 5432 port (see `docker-compose.yml` for 
  credentials) or just run `docker-compose up`. If you want your own credentials for DB connection, 
  just create application-<profileName>.yml and run spring boot app with your <profileName> or you 
  can define environment variables: `DB_URL`, `DB_USER` and `DB_PASSWORD`
* Run spring boot configuration in your IDE or `mvn spring-boot:run`

### Run tests

`mvn test`


## Working prototype

https://job-scheduler11.herokuapp.com/
