package com.zjy.baseframework;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Administrator on 2016/12/16.
 */
public class MongoDbHelper {
    public void test() {
        SimpleMongoDbFactory mongoDb = SpringContextHolder.getBean("mongoDbFactory");
        MongoDatabase test = mongoDb.getDb("test");

        //MongoCollection<Document> user = (MongoCollection<Document>)test.getCollection("user");
//        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
//        MongoCredential credential = MongoCredential.createCredential("root", "test", "root".toCharArray());
//        credentialList.add(credential);
//        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), credentialList);
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
//        MongoClient mongoClient = new MongoClient(
//                Arrays.asList(new ServerAddress("localhost", 27017),
//                        new ServerAddress("localhost", 27018),
//                        new ServerAddress("localhost", 27019)));
//        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
//        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("testdb");
        MongoCollection<Document> users = database.getCollection("testcollection");
        Document user = users.find().first();

        mongoClient.close();
    }
}
