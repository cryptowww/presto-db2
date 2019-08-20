# Presto DB2 connector

This is a plugin for Presto version 0.223 that allow you to use IBM DB2 Jdbc Connection


## Connection Configuration

Create new properties file inside etc/catalog dir:

    connector.name=db2
    connection-url=jdbc:db2://ip:port/database
    connection-user=myuser
    connection-password=mypassword

## Building Presto DB2 JDBC Plugin

    mvn clean install -DskipTests -Dair.check.skip-all=true

## DB2 JDBC Driver
```
$ mvn install:install-file \
-DgroupId=com.ibm \
-DartifactId=db2jcc \
-Dversion=10.1 \
-Dpackaging=jar \
-Dfile=/path/to/db2jcc4-10.1.jar
```
