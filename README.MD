# LBDS HTTP

Encapsulation of http objects for disentangled communication between SORMAS and LBDS 

## Requirements

* java 8 +
* maven 3.6 +

## Build

The component is built with maven

### regular JAR file as required by backend services
`mvn clean install`

### AAR build to make use in android
'mvn clean install -DskipTests=true -fpom-aar.xml -PyourProfile'

### Location of your SDK

There is a profile for each operating system in the pom.xml. You may add a profile for your needs.
Or you modify the path matching the exact location of your Android SDK

