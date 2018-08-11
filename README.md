# Simple shoes store with microservices

Implementation of a simplified version of the classical Pet Store (https://petstore.swagger.io/#/), but rather than pets we trade shoes with 3 microservices:

1. Shoe Service (CRUD on Shoe models)
1. Multimedia Service (CRUD on images of Shoes, can do maybe postprocessing or so)
1. Order and Inventory (Place an order, delete an order, get order status, get inventory status, works on SKU basis)
1. User Management (CRUD on user)


## Tech stack
- Http: http4s
- Stream: fs2 or akka-streams
- Kafka for pubsub
- Json for Kafka serialization, later: AVRO