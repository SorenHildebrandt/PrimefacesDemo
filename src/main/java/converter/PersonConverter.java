package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import entity.Skills;
import org.bson.types.ObjectId;

public class PersonConverter {
    // convert Person Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Skills p) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("name", p.getSkill()).append("country", p.getText1());
        if (p.getId() != null)
            builder = builder.append("_id", new ObjectId(p.getId()));
        return builder.get();
    }

    // convert DBObject Object to Person
    // take special note of converting ObjectId to String
    public static Skills toPerson(DBObject doc) {
        Skills p = new Skills();
        p.setSkill((String) doc.get("skill"));
        p.setText1((String) doc.get("country"));
        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
        return p;

    }

}
