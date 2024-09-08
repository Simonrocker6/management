# Getting Started

### Environment
Please make sure JDK version 17

### How to build & run backend server
cd to root folder 
mvn clean package
java -jar target/IncidentManagement-0.0.1-SNAPSHOT.jar

### End points
| HTTP Method | Endpoint              | Description                                      |
|-------------|-----------------------|--------------------------------------------------|
| GET         | `/api/incidents`      | List all incidents                               |
| POST        | `/api/incidents`      | Create a new incident                            |
| GET         | `/api/incidents/{id}` | Get an incident by ID                            |
| PUT         | `/api/incidents/{id}` | Update an existing incident by ID                |
| DELETE      | `/api/incidents/{id}` | Delete an incident by ID                         |

## You can run the react app as front end to better inspect the app.