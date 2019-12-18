package by.it.openWeatherMap;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;



public class MongoDB {

    public void saveInDB (int status, String date, String body) {

        try (MongoClient mongoClient = MongoClients.create(Parameters.MONGODB)) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("example");
            Document json = new Document("_id", new ObjectId());
            json.append("response_date", date)
                    .append("response", status)
                    .append("response_body", body);
            collection.insertOne(json);
        }
    }
}
