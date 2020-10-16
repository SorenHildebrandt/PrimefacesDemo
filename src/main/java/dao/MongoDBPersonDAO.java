package dao;

import com.mongodb.*;
import converter.PersonConverter;
import entity.Skills;
import entity.Student;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBPersonDAO {

	private DBCollection col;

	public MongoDBPersonDAO(MongoClient mongo) {
		this.col = mongo.getDB("journaldev").getCollection("Persons");
	}

	/*public Student createPerson(Student p) {
		DBObject doc = PersonConverter.toDBObject(p);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}*/

	public void updatePerson(Skills p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.update(query, PersonConverter.toDBObject(p));
	}

	public List<Skills> readAllPerson() {
		List<Skills> data = new ArrayList<Skills>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Skills p = PersonConverter.toPerson(doc);
			data.add(p);
		}
		return data;
	}

	public void deletePerson(Skills p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.remove(query);
	}

	public Skills readPerson(Skills p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		DBObject data = this.col.findOne(query);
		return PersonConverter.toPerson(data);
	}

}
