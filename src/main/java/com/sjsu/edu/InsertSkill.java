package job.recommendations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.sjsu.edu.recommendations.MongoDBConnection;


public class InsertSkill {

	
	
	public String insertSkills(HashMap<Long,List<Long>> skills) 
	{
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=null;
		//DBCursor cursor=null;
		
		
		try
		{
			dbobj=mongodb.getCollection("SkillsRecommendations");
			BasicDBObject document = new BasicDBObject();

			// Delete All documents from collection Using blank BasicDBObject
			dbobj.remove(document);
			BasicDBObject doc = null;
			for(Entry<Long,List<Long>>  entry: skills.entrySet())
			{
				System.out.println("called");
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			 doc = new BasicDBObject("recommendedSkills",entry.getValue()).append("user_id", entry.getKey());
			 System.out.println(doc);
			 System.out.println(dbobj.insert(doc));
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return "Skills Recommende for users is stored in database." ;
	}
	
	
	public ArrayList<String> getRecommendedSkills(long userID) throws UnknownHostException, MongoException
	{
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("SkillsRecommendations");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList skillID = new BasicDBList();
		whereQuery.put("user_id", userID);
		DBCursor cursor = dbobj.find(whereQuery);
		while(cursor.hasNext()) {
			skillID= (BasicDBList)cursor.next().get("recommendedSkills");
		    }
		
		ArrayList<String> recommendedSkills = getRecommendedSkillsName(skillID);
		return recommendedSkills;
	}
	
	
	
	
	
	
	public ArrayList<String> getRecommendedSkillsName(BasicDBList skillID) throws UnknownHostException, MongoException
	{
		
		System.out.println("This Called");
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("Skills");
		BasicDBObject inQuery = new BasicDBObject();
		ArrayList<String> recommendedSkills = new ArrayList<String>();
		inQuery.put("skillId", new BasicDBObject("$in", skillID));
		    DBCursor cursor = dbobj.find(inQuery);
		    while(cursor.hasNext()) {
		    	recommendedSkills.add((String)cursor.next().get("skill"));
		    }
		
		 return recommendedSkills;
	}
	
	
	public String insertJobs(HashMap<Long,List<Long>> jobs) 
	{
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=null;
		//DBCursor cursor=null;
		
		System.out.println("Insert recommended jobs in MongoDb called for all user");
		
		try
		{
			dbobj=mongodb.getCollection("JobsRecommendations");
			BasicDBObject document = new BasicDBObject();

			// Delete All documents from collection Using blank BasicDBObject
			dbobj.remove(document);
			BasicDBObject doc = null;
			for(Entry<Long,List<Long>>  entry: jobs.entrySet())
			{
//				System.out.println("called");
//				System.out.println(entry.getKey());
//				System.out.println(entry.getValue());
			 doc = new BasicDBObject("recommendedJobs",entry.getValue()).append("user_id", entry.getKey());
			 System.out.println("Job currently inserted in database");
			 System.out.println(doc);
			 System.out.println(dbobj.insert(doc));
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return "Skills Recommende for users is stored in database." ;
	}
	
	
	
	
}
