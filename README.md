# Musala Soft Gateway task
## Required environment to run this project
&#8594; OS must have installed java 8 or higher <br />
&#8594; OS must have installed MySQL Date base server <br />
&#8594; OS must have installed Nodejs 14.15.1 or higher

## Framework used to complete this task:
* Spring-boot to develop back end
* VueJs to develop front end

##To run this project follow the below instructions:
1. Change database credential in application.properties file according to your environment
2. Make sure 3000 port not occupied by any other application.

3. Open terminal and go to back-end folder of this project
4. Now execute the following command to run the back end server: <br />
   &#8594; ./mvnw clean && ./mvnw install && ./mvnw spring-boot:run

5. Open terminal and go to front-end folder of this project
6. Make sure 8090 port not occupied by any other application.
7. Now execute the following command to run the front end app: <br />
    npm install <br />
    npm run serve <br />


#####To explore front-end open the following link in browser
http://localhost:8090/