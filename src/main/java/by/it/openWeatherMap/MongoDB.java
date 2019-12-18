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
            MongoDatabase database = mongoClient.getDatabase(Parameters.DATABASE);
            MongoCollection<Document> collection = database.getCollection(Parameters.COLLECTION);
            Document json = Document.parse(body);
            Document document = new Document(Parameters.ID, new ObjectId());
            document.append(Parameters.DATE_RESPONSE, date)
                    .append(Parameters.STATUS_RESPONSE, status)
                    .append(Parameters.BODY_RESPONSE, json);
            collection.insertOne(document);
        }
    }
}
