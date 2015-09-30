# CouponDunia
Coupon Dunia Test. The scope of this system is to read mail details from database table and send 
emails in bulk over SMTP protocol. To boost the process, send bulk mails in parallel


It consists of following modules:  
1. Dal  
2. MailServer  
3. Model  
4. Core  
5. Tools  


Dal Module
----------
Data Access Layer- This module is responsible for accessing database. Currently it is using Spring JDBC Template 
to connect MySQL database. It exposes a Facade layer for clients to access DAL services

MailServer
----------
This module is responsible for sending Java Mails. It is integrated with Spring JavaX mail. It sends single 
and bulk mails. Mails of multiple types can be send (viz., PlainText, HTML, with or without attachments, etc). 
It exposes a Facade layer for clients to access MailServer services

Model
-----
This module exposes model data to be used across system

Core
----
Core module is where actual logic for the system is coded. It uses DAL services and MailServer services. It uses 
DAL service to get data from database and uses MailServer service to send mail. The core module manages a container 
`EmailContainer` which is populated by a continuously running thread `EmailProducer` and consumed by `EmailConsumerMaster` 

EmailProducer and EmailConsumer are linked together using `observer design pattern`

EmailProducer uses DAL service to populate the container. As soon as a new value is added in the container EmailConsumerMaster 
is invoked which then creates multiple `slaves` thread (only as many threads as needed) to read from container and 
send mail using MailServer services

`EmailProducer` is a continuously running thread which fetches data from database at a regular interval `configurable`. 
As soon as data is fed into the container and 'if consumer is not already' feeding on container then it gives a signal 
which invokes `EmailConsumerMaster` which in turn creates `x` number of slave threads (depending on capacity each thread can
take and also on current size of comtainer) to send mails. Once each slaves have done their job it is destroyed and Master is 
terminated. Number of emails each thread can sent is also configurable


Tools
-----
This module is independent. Purpose of this module is to create `dummy mails` in database, so that we can test our system. 
It creates `n` number of dummy mails in database (configurable)


How to run
----------
1. Clone the code from github [Coupon Dunia repository](git@github.com:asifsid88/CouponDunia.git)
2. Open your project in your favourite IDE 
3. Enable Annotation processing (As lombok is used in project)  
    If IDE is IntelliJ then Go to Preferences -> Type `enable annotations` -> Check `enable annotations processing` checkbox

![Enable Annotation Processing] (enable_annotation_processing.png)


Set up database
---------------
1. Create a database say `couponduniadb`
2. Add user to it  
```
Username: coupondunia    
Password: (0uPoNDuN!A  
```
3. Import sql scripts `couponduniadb_scripts.sql`


You can use any DB name and any password, but then you need to modify `properties` file to pick up correct configuration

Location of properties file
---------------------------
Depending upon the environment, corresponding properties files are created. For the simple purpose, say `dev` environment is being used. So all your configuration will be picked up from `dev.properties`  
Properties files are placed in `resources` folder. Open it and edit corresponding values

If you want to run any other environment, then pass `-Denv=environmentName` as a system properties value. Make sure respective `environmentName.properties` file exists in `resources->properties` folder    
**Example:** If you want to load `prod` environment. Then create `prod.properties` file in `resources->properties` folder and while running your application provide VM argument as `-Denv=prod`

How to create dummy emails
--------------------------
1. You need to run `Tools` Module
2. Set up database
3. Select your corresponding database (say `couponduniadb')
4. Import `couponduniadb_scripts.sql` to set up table and insert values
5. Set up environment values (by default `dev.properties` is loaded)
6. Create application configuration with Main Class as `com.asifsid88.coupondunia.tools.CreateDummyMail`
7. Select `Tools` as the classpath module
8. Run the configuration


By default, it will create 1000 mails in your database table `EmailQueue`  
Run `SELECT * FROM EmailQueue` to verify values

![Create Dummy Email Configuration](/CreateDummyMail_configuration.png)


How to run Core System
----------------------
1. You need to run `Core` Module
2. Set up database
3. Select your corresponding database (say `couponduniadb`)
4. Import `couponduniadb_scripts.sql` to set up table and insert values
5. Set up environment values (by default `dev.properties` is loaded)
6. Create application configuration with Main Class as `com.asifsid88.coupondunia.core.RunCouponDuniaSystem`
7. Select `Core` as the classpath module
8. Run the configuration

Assuming your database has dummy mails to sent

![Run Core System Configuration] (RunCouponDuniaSystem_configuration.png)








