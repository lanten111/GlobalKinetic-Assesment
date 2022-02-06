# GlobalKinetic-Assesment
User management application

#1.  End points

- #### PUT `api/users` - Create new user, not authenticationr required
    - request
  ````
  { 
  "username" : "user_name",
  "phone" : "phone",
  "password" : "user_password"
  }
  ````
  -Response
    ````
    {{username}} create succesfully
   ````


- #### GET - `api/users `- get list of all users that are registered , Authentication required
    - Response
   ````
  {
    [ 
      { 
       "id": "user_id", 
        "phone": "phone" 
       } 
    ]
  }

- #### POST - `api/login` - post username and password to login users

  - Request Body - Token will last for 60 seconds then it will expire
  ````
  {
    "username" : "username",
    "password": "password"
    }
  ````
  - Response
  ````
  {
    "id": id,
    "token": "token"
  }

- #### POST - `api/logout/{token}` logs out using token, uses path variable to pass token thats loggin out
    Notes -- unspecified whether to use request body or path variable, went with path variable
  - path variable
  ```
  {token}
  ```
  - Response
  ````
  204
  ````

# 2. LOGOUT MECHANISM

- jWT does not support invalidation of the token, unless the toke time has expired, it will stay alive.
- a way to logout users, would be to delete the token from users browser
 but still no way to do that on server side. to let the server know that the token is invalid, 
- improvised with adding an entity with blacklisted tokens,
  if the token exist in that table. then user will be unauthorized.

#3. How to Run the project

 - TO run the project build it first with ./gradlew build on unix or just gradlew build on Windows
   the build will run the test and grab all dependencies
 - to run the project use ./gradle bootrun / gradlew bootrun, 
   - The app will start up with embedded database 
   - To test, use the above endpoints
   - Client will be Postman, to make thing easy, if you are on Intellij, there is test-requests.http file with all endpoint and data ready
 - the project will start on port 8080 unless changed on property file
 - the application context root will be `/api `unless changed


#4. Test 
- There are test that wil run when the application build, the test will pull the whole application context with database
  - Test user create service
  - test get user list service
  - test token generation service and login
  - test token validation service

#5. Added stuff / Changed from assessment
- Tests -- see 4
- Invalid Token Entity
- Return json for user list, does not have the attribute "users"
- Create user endpoint will return use username as response on success

#6. Tech used
- Gradle
- Springboot
- GitHub
- H2(Database) - will start-up when application start, 




