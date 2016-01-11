# **doctorbatch.io**
## About
**doctorbatch.io** is provides two key capabilities to batch application developers adopting IBM's Java Batch platforms built on WebSphere Application Server:
- An application framework for development of batch applications in the Java programming language for deployment and parallel testing in WebSphere Batch ("traditional" WebSphere Application Server) and Java EE Batch (Liberty Profile v9 Feature with EE 7)
- A library of reusable batch artifacts (readers, writers, processors, listeners, etc) for which there are other IBM dependencies - for example:
	- Readers and Writers using IBM JZOS to access record oriented data in MVS
	- Processor implementations that invoke IBM Operational Decision Manager business rules applications

## Help!
Please send an e-mail to [tfanelli@zblservices.com](mailto:tfanelli@zblservices.com).

http://doc.torbat.ch

## Building from Source
To build from source, you must import several IBM product libraries as third-party JARs into your Maven repository. These include:
- IBM JZOS: jzos-2.4.jar, marshall-1.0.jar
- IBM WebSphere's Batch Runtime: com.ibm.ws.batch.runtime-8.5.5.jar
- IBM ODM's Libraries: jrules-res-session-java.jar

**jzos-2.4.jar**
```xml
<dependency>
  <groupId>com.ibm</groupId>
  <artifactId>jzos</artifactId>
  <version>2.4</version>
</dependency>
```
**marshall-1.0**
```xml
<dependency>
  <groupId>com.ibm</groupId>
  <artifactId>marshall</artifactId>
  <version>1.0.0</version>
</dependency>
```

**com.ibm.ws.batch.runtime-8.5.5.jar**
```xml
<dependency>
  <groupId>com.ibm.websphere</groupId>
  <artifactId>batch-runtime</artifactId>
  <version>8.5.0.0</version>
</dependency>
```

**jrules-res-session-java.jar**
```xml
<dependency>
   <groupId>com.ibm</groupId>
   <artifactId>jrules-res-session-java</artifactId>
   <version>8.7.0.0</version>
 </dependency>
```

## Downloading the **doctorbatch.io** Library
The current release is available for download under the "dist/" folder

## Getting Started
Documentation to get started quickly, along with a sample application, are coming very soon!

## About This Fork
All future maintenance and development will be managed under this fork of the project. Tim Fanelli (aka, Doctor Batch), left IBM in October 2015 to form ZBL Services, Inc. As Tim was the sole maintainer of the project at IBM, the folks over at WASDev suggested that he fork the project, and retain maintenance over it. The original source at the WASDev repository should be considered deprecated.
