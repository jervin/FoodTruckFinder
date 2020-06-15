# FoodTruckFinder
This application represents both a command line application and a web service to find the nearest set of Food Trucks.

# To Run
1. Make sure you have Java 9 and Maven 3.6.3 installed.
2. Either clone this repository or use the link in GitHub to download it.
3. Invoke mvn clean spring-boot:run.

This application can also be run as a command line application.  The same jar is used for the web service and the command line application.  If the command line contains -lat  and -lon options, it assumes that the user wants to invoke the service as a command line application, will call the business logic to find the trucks directly and then exit.  If the command line arguments do not contain lat/lon then it is assume that the user wants the Spring Boot application started as a web service.
1. Build the application to get the jar file generated.  By Maven convention under the subfolder target.
2. java -jar FoodTruckFinder-<version>.jar along with the following command line arguments:
    - -lat (latitude) - required
    - -lon (longitude) - required
    - -foodType (greps the term in the FoodItems field to let you search for Chinese, Mexican food or something specific like Burritos) -optional
    - -mi (distance from the lat/lon the trucks must be in specified in miles) - optional

