package job.recommendations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.sjsu.edu.recommendations.MongoDBConnection;

public class ExistingSkills {

	public HashMap<String, Long> getExistingSkills(long userID)
			throws UnknownHostException, MongoException {

		HashMap<Long, Long> existingSkills = new HashMap<Long, Long>();

		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("ratings");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList skillID = new BasicDBList();
		whereQuery.put("userId", userID);
		DBCursor cursor = dbobj.find(whereQuery);
		Map temp;
		while (cursor.hasNext()) {
			temp = cursor.next().toMap();
			existingSkills.put((Long) temp.get("skillId"),
					(Long) temp.get("proficiency"));
		}

		HashMap<String, Long> existingSkillwithNames = getExistingSkillName(existingSkills);

		return existingSkillwithNames;
	}

	public HashMap<String, Long> getExistingSkillName(
			HashMap<Long, Long> userSkills) throws UnknownHostException,
			MongoException {

		HashMap<String, Long> existingSkills = new HashMap<String, Long>();

		System.out.println("#######################");
		System.out.println(userSkills);

		System.out
				.println("This Called ##########################################");
		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("Skills");
		BasicDBObject inQuery = new BasicDBObject();
		ArrayList<String> recommendedSkills = new ArrayList<String>();

		for (Long i : userSkills.keySet()) {
			inQuery.put("skillId", i);
			DBCursor cursor = dbobj.find(inQuery);
			existingSkills.put("" + cursor.next().get("skill"),
					userSkills.get(i));
			inQuery.clear();

		}
		System.out.println("This Called ######--------------------#######"
				+ existingSkills);
		return existingSkills;
	}

	public ArrayList<Integer> getExistingSkillsList(long userID)
			throws UnknownHostException, MongoException {
		ArrayList<Integer> existingSkills = new ArrayList<Integer>();

		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("ratings");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList skillID = new BasicDBList();
		whereQuery.put("userId", userID);
		DBCursor cursor = dbobj.find(whereQuery);
		Map temp;
		while (cursor.hasNext()) {
			temp = cursor.next().toMap();
			existingSkills.add((Integer) temp.get("skillId"));
		}

		return existingSkills;

	}

}
