# chat-service
Service for chatting room

# Tech Stack:
-----------
Java 17 <br>
Spring 3.X <br>
H2 In Memory DB <br>
Maven <br>

# Build Process:
---------------
Clone project to folder
Open terminal inside the project root folder
Build project: mvn clean install <br>
Run project: mvn spring-boot:run  <br>

# POST Man  API testing
------------------------
Import the Chat Room Service collection to your postman it is available under project resources folder.
Postman collection having API's for login, delete message by client, fetch message by client and fetch messages by chatroom

# Open API Details:
------------------
http://localhost:8080/api-docs <br>

# Swagger Details: PFB swagger for API details
----------------------------------------------
http://localhost:8080/swagger-ui/index.html#/ <br>

# WebSocket testing
-----------
for websocket testing open http://localhost:8080/index.html in multiple windows and enter with different user names send messages.

Docker Image Build Commands
--------------------
docker build -t chat-service .


H2- DATABASE
-------------
http://localhost:8080/h2-console <br>
username: sa <br>
password: password<br>