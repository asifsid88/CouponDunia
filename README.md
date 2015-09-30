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








