# ELK example app

Simple spring application for ELK training with log4j2 lib

There are three supported GET request methods
/validrequest with Hello world page
/exception - null pointer exception page with printed status on page
/elk - this one trying to make requests to /validrequest and /exception pages

## Getting Started

Build project

```mvn install```

Run sample app and try it on localhost:8080 (by default)

```java -jar target/elk-sample-app-0.0.1-SNAPSHOT.jar```

Example application.properties config used in app
```
logging.file=elk-example.log
logging.level.net.express42.*=DEBUG
spring.application.name=elk-sample-app
```

### Prerequisites

Java 7+ 

## Built With
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 