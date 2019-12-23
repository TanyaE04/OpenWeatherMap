# OpenWeatherMap
This application allows to receive weather data on a schedule (during specified period of time) and save this data as a json document in the MongoDB.



# Requirements

 Register on the <https://openweathermap.org/> and get a unique key (APPID)
 Install the database MongoDB - MongoDB Community Server 4.2.2
 Maven 3.6.3


# Getting started
  
After clone the project, in the "Confif.properties" (src-main-resources) set the required parameters (uri database, database and collection name, APPID key, name of City, schedule).

Then at the root of the project:
      > mvn compile  //compile the application
      mvn exec:java -Dexec.mainclass="by.it.openWeatherMap.Main"  //launch the application

To check (watch) the saving weather data you need:
      mongo //connect to a MongoDB on your localhost with default port 27017
      use test //choose the database
      db.openweathermap.find() //document from collection
