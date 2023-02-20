### Distributed Systems Assignment

Note: This is the first part of the assignment for the Distributed Systems class

## Rooms

In this application users can make their own room or be members of other rooms.
They can post on the rooms so other members can see it.


---

### Running it Locally

```
1. git clone https://github.com/kon-bikas/Rooms.git
2. sudo docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=students --net=host -v pgdata14:/var/lib/postgresql/data  -d postgres:14
```
Then run the tomcat server and make request in `localhost:8080`


