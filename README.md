# OpenWeatherMap
This application allows to receive weather data on a schedule (during specified period of time) and save this data as a json document in the MongoDB.



# Requirements

 Register on the <https://openweathermap.org/> and get a unique key (APPID)
 Install the database MongoDB - MongoDB Community Server 4.2.2
 Maven 3.6.3


# Getting started

After clone the project, in the "Confif.properties" (src-main-resources) set the required parameters (uri database, database and collection name, APPID key, name of City, schedule).

Then at the root of the project:
```
     //compile the application
     mvn compile
     //launch the application
     mvn exec:java -Dexec.mainClass="by.it.openWeatherMap.Main
```

To check (watch) the saving weather data you need:
```
      //connect to a MongoDB on your localhost with default port 27017
      mongo
      //choose the database
      use test
      //document from collection
      db.openweathermap.find()
```


  
