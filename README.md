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
- Send a request (I use HTTPie:  http POST `http POST http://127.0.0.1:9092/shoes id=user123  username=PaulAnka firstName=Paul lastName=Anka email=paulanka@gmail.com password=diana status=Active`)