package by.it.open_weather_map;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.types.ObjectId;


public class MongoDB {

    private MongoClient mongoClient;
    private static final MongoDB instance = new MongoDB ();

    private MongoDB() {
        mongoClient = MongoClients.create (Parameters.MONGODB);
    }

    public static MongoDB getInstance() {
        return instance;
    }

    public void insertIntoDB(WeatherData weatherData) {

        MongoDatabase database = mongoClient.getDatabase (Parameters.DATABASE);
        MongoCollection<Document> collection = database.getCollection (Parameters.COLLECTION);
        String index = collection.createIndex (Indexes.descending ("response_date"));
        Document json = Document.parse (weatherData.getBody ());
        Document document = new Document (Parameters.ID, new ObjectId ());
        document.append (Parameters.DATE_RESPONSE, weatherData.getDate ())
                .append (Parameters.STATUS_RESPONSE, weatherData.getStatus ())
                .append (Parameters.BODY_RESPONSE, json);
        collection.insertOne (document);
    }
}
