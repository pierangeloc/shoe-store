# Simple shoes store with microservices

Implementation of a simplified version of the classical Pet Store (https://petstore.swagger.io/#/) in a microservice-based fashion, and rather than pets we trade shoes.

Microservices are:

1. Shoe Service (CRUD on Shoe models)
1. Multimedia Service (CRUD on images of Shoes, can do maybe postprocessing or so)
1. Order and Inventory (Place an order, delete an order, get order status, get inventory status, works on SKU basis)
1. User Management (CRUD on user)


## Tech stack
- Http: http4s
- Stream: fs2 or akka-streams
- Kafka for pubsub
- Json for Kafka serialization, later: AVRO

## Run it
- Start UsersMicroService (TODO: provide full sbt cmd)
- Send a request (I use HTTPie:  http POST `http -v POST http://127.0.0.1:9092/shoes id:=123  username=PaulAnka firstName=Paul lastName=Anka email=paulanka@gmail.com password=Diana status=Deleted`), the user will be saved and reported as output:
```
http -v POST http://127.0.0.1:9092/shoes id:=123  username=PaulAnka firstName=Paul lastName=Anka email=paulanka@gmail.com password=Diana status=Deleted
POST /shoes HTTP/1.1
Accept: application/json, */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 149
Content-Type: application/json
Host: 127.0.0.1:9092
User-Agent: HTTPie/0.9.9

{
    "email": "paulanka@gmail.com",
    "firstName": "Paul",
    "id": 123,
    "lastName": "Anka",
    "password": "Diana",
    "status": "Deleted",
    "username": "PaulAnka"
}

HTTP/1.1 200 OK
Content-Length: 136
Content-Type: application/json
Date: Thu, 16 Aug 2018 22:32:07 GMT

{
    "email": "paulanka@gmail.com",
    "firstName": "Paul",
    "id": 123,
    "lastName": "Anka",
    "password": "Diana",
    "status": "Deleted",
    "username": "PaulAnka"
}
```