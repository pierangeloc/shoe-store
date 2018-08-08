# Simple shoes store with microservices

Implementation of a simplified version of the classical Pet Store (https://petstore.swagger.io/#/), but rather than pets we trade shoes with 3 microservices:

1. Pet Service (CRUD on Shoes, meant as SKU)
1. Multimedia Service (CRUD on images of Shoes, can do maybe postprocessing or so)
1. Order and Inventory (Place an order, delete an order, get order status, get inventory status)
1. User Management (CRUD on user)


## Tech stack
- Http: http4s
- Stream: fs2 or akka-streams
- Kafka for pubsub
- Json for Kafka serialization, later: AVRO