# Library
The app uses PostgreSQL 10.6, Spring Boot 2.2.10  
For the first run, you have to uncomment 2 last lines in application.properties and comment them back after the first run.
API uses HTTP basic auth to authenticate users. Swagger 2 documentation located at "http://localhost:8080/swagger-ui.html"  
  
### **Authentication**  
To check if credentials are ok you may use /ping endpoint. If credentials are ok you would get "pong" response.  
  
###  **BookController**  
##### GET  
/book  
Returns a list of all books.  
Parameters (Query string):  
filter(optional), if the parameter is provided, it searches for references of inputted string in the book.name and book.about
  
/book/{id}  
Returns information about the book by its id    
  
##### POST  
/book  
Creates a new book  
Parameters (JSON):  
name(String, required)  
author(Object, required) which contains only id  
about(String, required)  
tag(Object, required) which contains only id  
  
Example JSON request body:  
{  
 "name": "String",  
	"author":{  	
	    "id": 0  
	},  
 "about": "String",  
 "tag":{  	
	    "id": 0  
	},  
}  
  
##### PUT  
/book/{id}  
Updates a book by its id  
Parameters (JSON):  
name(String)  
author(Object) which contains only id  
about(String)  
tag(Object) which contains only id  
The JSON itself is like previous one  
  
##### DELETE  
/book/{id}  
Deletes a book by its id  
  
###  **TagController**  
##### GET  
/tag  
Returns a list of all tags.  
  
/tag/{id}  
Returns information about the tag by its id    
  
##### POST  
/tag  
Creates a new tag  
Parameters (JSON):  
name(String, required)  
  
Example JSON request body:  
{  
 "name": "String"    
}  
  
##### PUT  
/tag/{id}  
Updates a tag by its id  
Parameters (JSON):  
name(String)  
The JSON itself is like previous one  
  
##### DELETE  
/tag/{id}  
Deletes a tag by its id  

  ### **UserController**  
  ##### GET  
  /users  
  Returns a list of all registered users   
    
  /users/{id}  
  Returns information about a user by its id  
  
  
  ##### POST  
  /register  
  Register a new user  
  Parameters (JSON):  
  firstName (First name)  
  lastName (Last name)   
  email (User email, required)  
  password (User password, required)  
  
  Example JSON request body:  
  {  
  	"firstName": "Fname",  
  	"lastName": "Lname",   
  	"email": "example@mail.box",  
  	"password": "pass"  
  }
  
