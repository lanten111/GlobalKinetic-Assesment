# GlobalKinetic-Assesment
User management application

1. # End points

- #### PUT `api/users` - Create new user, not authentication required
    - request
  ````
  { 
  "username" : "user_name",
  "phone" : "phone",
  "password" : "user_password"
  }
  ````
  - Response

  ````
    {{username}} created succesfully
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

- #### POST - `api/logout/{token}` logs out using token, uses path variable to pass token that will be invalidated
    Notes -- unspecified whether to use request body or path variable, went with path variable
  - path variable
  ```
  {token}
  ```
  - Response
  ````
  204
  ````

2. # LOGOUT MECHANISM

- jWT does not support invalidation of the token, unless the toke time has expired, it will stay alive.
- a way to logout users, would be to delete the token from users browser
 but still no way to do that on server side. to let the server know that the token is invalid, 
- improvised with adding an entity with blacklisted tokens,
  if the token exist in that table. then the user who attempt to send request with the token will be unauthorized.

3. # How to Run the project

 - To run the project, build it first with `./gradlew build` on unix or just `gradlew build` on Windows
   the build will run the test and grab all dependencies
 - to run the project use `./gradlew bootrun` or `gradlew bootrun`, 
   - The app will start up with embedded database 
   - To test, use the above endpoints
   - Client will be Postman,no front end to make things easy, if you are on Intellij, there is `test-requests.http` file under `test/java/co.za.globalkimetic.Assesment` with all endpoint and data ready
 - the project will start on port 8080 unless changed on property file
 - the application context root will be `/api `unless changed on property file
 - full context path `localhost:8080/api/`


4. # Test 
- There are test that wil run when the application build, the test will pull the whole application context with database
  - Test user create service
  - test get user list service
  - test token generation service and login
  - test token validation service

5. # Added stuff / Changed from assessment
- Tests -- see 4
- Invalid Token Entity
- Returned json for user list does not have the attribute "users"
- list user endpoint will return user username as response on success

6. # Tech used
- Gradle
- Springboot
- GitHub
- H2(Database) - will start-up when application start, 




