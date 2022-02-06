# GlobalKinetic-Assesment
User management application

1. End points

- PUT - api/users - Create new user, not authenticationr required 
  - request
  ````
  { 
  "username" : "user_name",
  "phone" : "phone",
  "password" : "user_password"
  }
  ````
  -Response - 204


- GET - api/users - get list of all users that are registerd
Authentication required

- POST - api/login - post username and password to login users

- POST - api/logout/{token} -logs out using token, uses path variable to pass token thats loggin out
Notes -- unspecified whether to use request body or path variable

2. LOGOUT MECHANISM

- jWT does not support invalidation of the token, unless the toke time has expired, it will stay alive.
- a way to logout users, would be to delete the token from users browser
 but still no way to do that on server side. to let the server know that the token is invalid, 
- improvised with adding an entity with blacklisted tokens
  if the token exist in that table. then it invalid.

3. How to Run the project

 - TO run the project build it first with ./gradlew build on unix or just gradlew build on Windows
   the build will run the test and grab all dependencies
 - to run the project use ./gradle bootrun / gradlew bootrun
 - the project will start on port 8080 unless changed on property file
 - the application context root will be /api unless specified



