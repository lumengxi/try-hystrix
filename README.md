# try-hystrix

Hystrix playground.

## Usage

### Run the application locally

`lein ring server`

Then you can test Hystrix behavior using:

`curl -H "Content-Type: application/json" -d '{"n": 10}' -X GET http://localhost:3001/api/ping`

`n` here means the app will hit an [online JSON server](https://jsonplaceholder.typicode.com/posts) to retrieve `n` number 
of posts' title, and send back to the client. The response will have different failures, including 404, timeout etc. 

### Run the tests

`lein test`

### Access Hystrix Event Streams
The event stream is exposed at [localhost:3000/hystrix.stream](localhost:3000/hystrix.stream), to view these stream on 
Hystrix dashboard, follow the instructions [here](https://github.com/Netflix/Hystrix/tree/master/hystrix-dashboard) to start the dashboard.

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/try-hystrix.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright ©  Mengxi Lu
