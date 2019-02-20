# customer-management-basic
This example is to enable the customers of the organisation to manage their profile (end to end i.e. create, update and delete) through web channel and mobile (app) channel. 

This example provide API to list, get, create, update, delete customers with the Oauth2 authentication with the following assumtions:
1. The backend system is an enterprise CRM and is not yet ready to expose REST APIs. The CRM team is in the process of designing their REST APIs. 
2. Client and users are set in-memory. Will be updated to DB based authentication in stage 2
3. Mobile and Web FE will use the same oauth mechanism with client ID. Will be updated to different client ID and different token expiration time for mobile and web in stage 2

Will be upgraded to Kotlin and WebFlux later on
