# XYZ Bank Token System instructions:

Do checkout the project from github using the link : https://github.com/RajuKalagoni/BankTokenSystem

It's a maven project, install java, any IDE (recommended to use Intellij) and add the project as Maven Project
and do Maven clean, install and then run it either in Run/Debug mode.

Now, API is available for testing, use postman for testing.

## API exposed:

GET: http://localhost:8080/xyz/tokens - gives all tokens counters wise.

GET: http://localhost:8080/XYZ/token/waitingCustomers - gives Customers list who have not assigned a counter yet.

GET: http://localhost:8080/xyz/token/{id} - gives specific token Details.

PUT: http://localhost:8080/xyz//tokens/{id}/type - tells type of service assigned to the particular token id.

Below API does basic CRUD operations of Token and Customer.

POST: http://localhost:8080/xyz/token - creates token with given details.

DELETE: http://localhost:8080/xyz/tokens/{tokenId} - Deletes given token.

GET: http://localhost:8080/xyz/customers - gives all customers list

GET: http://localhost:8080/xyz/customers/{custId} - gives specific customer details.

GET: http://localhost:8080/xyz/customer/{id}/premiumType - gives specific customer service Type.

PUT: http://localhost:8080/xyz/customer/{customerId} - updates specific customer details.

DELETE: http://localhost:8080/xyz/customer/{customerId} - Deletes given customer.


# Business Logic:

* For this token system, two Singleton classes are maintained.

* When a customer arrives, a token will be generated and if serving counters are availble counter will be assigned otherwise tokens will be placed in Premium/Regular queue depending upon the customer service type. When a token is served, then counter will be assigned to token based on the priority.

* Enums are used to accept specific values for Priority Type(PREMIUM/REGULAR) and Status(OPEN/COMPLETED/CANCELLED/FORWARDED).

* Exceptions are handled with proper messages and HTTP status codes.

* While updating token or customer, it is checked whether a token or customer exists with the given ID or not, If it doesn't exist it throws Customer/Token not found with id:{Id}.

* covered Junit test cases using Mockito library.

* And also mentioned ER diagram : BankTokenSystem_ER.png

