# OpenWeatherMap
This application receives weather data by the schedule (in some time interval). The received data is stored in the database MongoDB. 
MongoDB's documents contain information not only about weather but also about response (data and status code).
Settings are located in folder "resources-config.properties" (key=value).
You could change:
  the schudule of load application (sets by cron expressions), 
  DB url, database's and collection's names of saving information, 
  city to get weather data (default - Minsk)
  appid - unique key for access OpenWeatherMap API (get after registration)
  
