import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import entity.Skills;
import org.bson.Document;

import javax.inject.Inject;



public class SkillFacade {

    @Inject
    private transient MongoClient mongoClient;

    public void create(Skills skills){
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("skills");
        if (skills !=null){
        Document d = new Document().append("id", skills.getId())
            .append("skill",skills.getSkill())
            .append("text1",skills.getText1())
            .append("text2",skills.getText2());
        collection.insertOne(d);
        }
    }
}
