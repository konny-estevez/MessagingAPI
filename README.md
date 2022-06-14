# Readme

## Messaging API

This project is a simple example of the use of Spring Boot to create an API with some basic and standard features for a modern and secure API implementation. 
Currently the project is implemented using some built-in features on Spring like the following:

- Spring Web for REST application support
- Spring JPA for database access with ORM support
- Spring Security for securing the application against attacks of hackers
- Spring Validation for validating the user input with annotations

Also, the application uses JSON Web Tokens with an external library to provide secure access to the endpoints avoiding the vulnerability of security with the exposure of user credentials to travel through the network in every request if the application was using Basic Authentication.

FInally, the application uses the current the facto standard for documentation with swagger external library to document the API in an easy way to provide the necessary documentation for developers to use the API.
