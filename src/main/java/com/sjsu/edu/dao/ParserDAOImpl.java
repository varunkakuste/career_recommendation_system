package com.sjsu.edu.dao;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ParserDAOImpl implements IParserDAO {
	
	@Override
	public Map<String, Integer> getSkills() {
		Map<String, Integer> skillsMap = null;
		MongoDB mongoDB = null;
		DBCollection dbobj = null;
		DBCursor cursor = null;
		try {
			skillsMap = new HashMap<String, Integer>();
			mongoDB = new MongoDB();
			dbobj = mongoDB.getCollection("Skills");
			cursor = dbobj.find();
			while(cursor.hasNext()) {
				DBObject objQuery = cursor.next();
				skillsMap.put(objQuery.get("skill").toString().toLowerCase(), Integer.valueOf(objQuery.get("skillId").toString()));
			}
		} catch (Exception expt) {
			System.out.println(expt);
		}
		return skillsMap;
	}
	
}
