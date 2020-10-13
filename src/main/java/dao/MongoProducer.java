package dao;

import com.mongodb.MongoClient;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import java.rmi.UnknownHostException;


@ApplicationScoped
public class MongoProducer {

    MongoClient mongoClient;
    @Produces
    public MongoClient create() throws UnknownHostException {
        System.out.println("public MongoClient create");
        mongoClient = new MongoClient();
        return mongoClient;
    }

    public void close(@Disposes final MongoClient mongoClient) {
        System.out.println("public MongoClient close");
        mongoClient.close();
    }
}


