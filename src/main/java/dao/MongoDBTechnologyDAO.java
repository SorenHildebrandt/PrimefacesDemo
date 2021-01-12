package dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import converter.TechnologyConverter;
import entity.Technology;
import org.bson.types.ObjectId;

public class MongoDBTechnologyDAO {
    private DBCollection col;

    public MongoDBTechnologyDAO(MongoClient mongo) {
        this.col = mongo.getDB("hildebrandt-udvikling").getCollection("technology");
    }

    public Technology readTechnology(Technology technology) {
        System.out.println("readTechnology");
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(technology.getId())).get();
        DBObject data = this.col.findOne(query);
        System.out.println("readTechnology DBObject query " + query);
        System.out.println("readTechnology DBObject data " + data);

        return TechnologyConverter.toTechnology(data);
    }
}
