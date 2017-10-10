# Food-Truck
This application provides data about frod trucks near particular location. It helps you to find out food when you are hungry and finding some food near you.

Pre requisites:

OS- windows 8.1
JAVA - jdk 1.8,
IDE - Intellij IDEA IDE,
build tool - Gradle 2.9

To run applcation in local system:

1. To Open project in Intellj IDEA open project location and open build.gradle fle with Intellj IDEA. so, one dialouge box for project settings will be opened.
2. check Use auto-import opton then select your installed gradle location and JDK location.so,  project will be opened in IDE.
3. open Terminal window inside IDE for this press Alt + F12 key.
4. Run below command in terminal:
    gradle clean build
5. Open class FoodTruckApplication. open Right click menu and select open Run FoodTruckApplication.
6. Open browser and hit below URL:
http://localhost:8443/foodtruck/foodTrucks?longitude=37.789119207668&latitude=-122.395881039335

NOTE: This application gives food truck list within 1000m radius from given location. If there is no food trucks available within this radius then it will search upto 5000m radius.
