## Installation

# Database
Download xampp
[https://www.apachefriends.org/index.html](https://www.apachefriends.org/index.html)

# Java
Install Java (open jdk-14) [https://jdk.java.net/14/](https://jdk.java.net/14/)

Set System/Environment variable of

JAVA_HOME

In PATH add

%JAVA_HOME%\bin

If have weird issue when typing java -version in cmd

Delete C:\ProgramData\Oracle

Make sure java.exe, javaw.exe, javaws.exe are removed

# Maven
Set System/Environment variable of

MAVEN_HOME

M2_HOME

In PATH add

%MAVEN_HOME%\bin

%M2_HOME%\bin

# Eclipse
Install Eclipse [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/)

## How to Run
# MySQL
Start xampp control panel and start MySQL

Create DB with MySQL using cmd

```cmd
*\xampp\mysql\bin> mysql -u root
Maria [(none)]> create database grantdb;
Maria [(none)]> create user employee1@localhost identified by 'random#Password$123';
Maria [(none)]> grant all on grantdb.* to employee1@localhost
```

# Eclipse
Import project as maven project

Double click on App.java and run to run application

Double click on HouseholdGrantTest.java/HouseholdIntegrationTest to run test
