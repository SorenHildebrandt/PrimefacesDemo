import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class rodekasse
       /* System.out.println("load mongp");

        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoDatabase cvBankDB = mongoClient.getDatabase("cvbank");
        MongoCollection<Document> biodataCollection = cvBankDB.getCollection("biodata");
        //DB db = mongoClient.getDB("wildflyschema");


        //DBCollectiontable = cvBankDB.getCollection("car");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("manufacturer", "Ferrari");

        //DBCursor cursor = biodataCollection.find(

        *//*while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            Cvbank cvbank = new Cvbank((String) document.get("model"),
                    (Date) document.get("date"),
                    (String) document.get("manufacturer"),
                    (String) document.get("color"));

            cvBankList.add(Cvbank);
        }*//*
    }
*/
{


    /*//try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
    MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
    //database = mongoClient.getDatabase("javaguides");
    MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
    //MongoCollection < Document > mongoCollection =  database.getCollection("users");
    MongoDatabase cvBankDB = mongoClient.getDatabase("cvbank");
    MongoCollection<Document> biodataCollection = cvBankDB.getCollection("biodata");
    //Consumer<Document> printBlock = document -> System.out.println(document.toJson());
    //System.out.println(printBlock);
            *//*try (MongoCursor< Document > cur = collection.find().iterator()) {
                while (cur.hasNext()){
                System.out.println("try mongocursor");
                    //var doc = (var) cur.next();
                    *//**//*var doc = cur.next();
                    var biodata = new ArrayList < > (doc.values());*//**//*

                }
            }*//*
    // }*/


}
