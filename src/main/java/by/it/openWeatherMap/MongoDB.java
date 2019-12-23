package by.it.openWeatherMap;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;


public class MongoDB {

    private MongoClient mongoClient;
    private static final MongoDB instance = new MongoDB();

    private MongoDB() {
        mongoClient = MongoClients.create(Parameters.MONGODB);
    }

    public static MongoDB getInstance() {
        return instance;
    }

    public void insertIntoDB(int status, Date date, String body) {

        MongoDatabase database = mongoClient.getDatabase(Parameters.DATABASE);
        MongoCollection<Document> collection = database.getCollection(Parameters.COLLECTION);
        String index = collection.createIndex(Indexes.descending("response_date"));
        Document json = Document.parse(body);
        Document document = new Document(Parameters.ID, new ObjectId());
        document.append(Parameters.DATE_RESPONSE, date)
                .append(Parameters.STATUS_RESPONSE, status)
                .append(Parameters.BODY_RESPONSE, json);
        collection.insertOne(document);
    }
}
