Journal Application using Spring boot, hibernate,kafka, Redis, mongoDb and Spring security 


Rest API Best Practices

1. CRUD to Rest
2. Reliability
3. Fine Tunning  API -> Versioning -> header versioning X-Api-version
4. Securing  API 
5. Performance and Rate Limiting -> Caching 
6. Documentation 


Api Security
I. Input validation and sanitization
II. Monitoring and Logging 
III. Data Encryption 
IV. Regular database audits
V. Penetration Testing 
VI. Authentication and authorization  

JWT : JSON  WEB TOKEN
1.  Secure Client Storage -> XSS, CSRF
2. Token Expiration
3. Validate


I. Richardson Maturity Model
1. Level 0 -> Remote Procedure Call
2. Level 1 -> Resources
3. Level 2 -> HTTP Verbs
4. Level-3 -> HATEOAS

II. Stateless -> Cacheable

III. Endpoints -> 
        1. No Trailing forward slash
        2. Hierarchical relationships
        3. Use hyphens
        4. No actions 
        5. Use Plurals 
        6. Avoid Complexity
IV. Responses 
V. Exceptions
VI. Versioning
VII. HATEOAS-> Hypermedia As The Engine Of Application State
 
Disadvantages of HATEOAS
 1.  Performance cost
 2. No Accepted Standerd
 3. Low Adoption 


Java Garbage Collector

* Java garbage collection is an automatic process that manages memory in the heap.
* It identifies which objects are still in use (referenced) and which are not in use (unreferenced).
* It removes the objects that are unreachable (no longer referenced).
	•	The programmer does not need to mark objects to be deleted explicitly. The garbage collection implementation lives in the JVM. 

Types of Activities in Java Garbage Collection
Two types of garbage collection activities usually happen in Java. These are:
1. Minor or incremental Garbage Collection (GC): This occurs when unreachable objects in the Young Generation heap memory are removed.
2. Major or Full Garbage Collection (GC): This happens when objects that survived minor garbage collection are removed from the Old Generation heap memory. It occurs less frequently than minor garbage collection.


JPA/Hibernate Advanced

ORM-> Object Relational Mapping 
Object-Relational Mapping (ORM) is the process of converting Java objects to database tables. 
In other words, this allows us to interact with a relational database without any SQL.

JPA-> Java Persistence API
The Java Persistence API (JPA) is a specification that defines how to persist data in Java applications. 
The primary focus of JPA is the ORM layer.


Hibernate

 Configuration, Session , SessionFactory  Transaction ;

Details of the session, 
configuration, 
sessionFactory->  heavy weight object , loose lot of resources , if forget to close it , it auto close , 
Transaction-> only use for save, update data into DB;
