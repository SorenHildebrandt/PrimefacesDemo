package converter;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import entity.Technology;

import java.util.List;

public class TechnologyConverter {

	// convert DBObject Object to Technology
	// take special note of converting ObjectId to String
	public static Technology toTechnology(DBObject doc) {
		System.out.println("toTechnology data mongo " + doc);
		Technology technology = new Technology();

		technology.setId_integer((Integer) doc.get("id_integer"));
		technology.setSelectedTechnologies((List<String>) doc.get("selectedTechnologies"));
		technology.setRichText1((String) doc.get("richText1"));

		ObjectId id = (ObjectId) doc.get("_id");
		technology.setId(id.toString());
		return technology;

	}
	
}
