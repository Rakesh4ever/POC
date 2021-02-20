# POC
```diff
!  Micro services with Java 11, Spring boot 2.x and  webflux + WebClient + Spring data Cassandra
```
## Design is shown below:
```diff
+ In this technical design you would get the an architectural diagram of microserces that represents 
+ top to bottom approach design. Internally, the conceptual process/request start with users/clients 
+ that call the services, request goes to the API Gateway and then according to the configuration it 
+ calls to the Zuul masking the hardcore url, Eureka -Service registry -> finds the appropriate services
+  and return to caller and then caller calls to the suggested api/services.   
```
![MySoftware_Design](https://user-images.githubusercontent.com/48691043/108583601-8461f900-7360-11eb-879e-6b0c3d631484.png)

