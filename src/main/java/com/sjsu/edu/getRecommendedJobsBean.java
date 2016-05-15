package job.recommendations;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.sjsu.edu.recommendations.MongoDBConnection;


public class getRecommendedJobsBean {
	
	
	static MongoDBConnection mongodb = new MongoDBConnection();
	static MongoDBConnection mongodb1 = new MongoDBConnection();
	
	
	public void getRecommendedJobData() throws UnknownHostException, MongoException{
		
		DataModel dbm;
		try {
			dbm = new FileDataModel(new File("C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\SkillPreferencces.csv"));

			HashMap<Integer, List<Integer>> recommendedJobs = new HashMap<Integer, List<Integer>>();
			DBCollection recommendedJobDetail=mongodb1.getCollection("RecommendedJobsforUsers");		
			BasicDBObject document = new BasicDBObject();

			// Delete All documents from collection Using blank BasicDBObject
			recommendedJobDetail.remove(document);
			
			
			for(LongPrimitiveIterator users = dbm.getUserIDs(); users.hasNext();) {
				long userId=users.nextLong();
				System.out.println(userId);
		
		System.out.println("Get Recommended Job Data method called for User "+userId);
		
//		ArrayList<JobBean> jobData = new ArrayList<JobBean>();
		

		mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("JobsRecommendations");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList jobList = new BasicDBList();
		BasicDBList tempJobList = new BasicDBList();
		whereQuery.put("user_id", userId);
		DBCursor cursor = dbobj.find(whereQuery);
		while(cursor.hasNext()) {
			jobList= (BasicDBList)cursor.next().get("recommendedJobs");
			System.out.println("Recommended Jobs for User "+userId);
			System.out.println(jobList);
			System.out.println("Recommended Job Count for User " + cursor.count());
		    }
		
	
//		 jobData = getRecommendedJobsData(userId, jobList);
		getRecommendedJobsData(userId, jobList);
			}
		}
		catch(Exception e)
		{
			System.out.println("job recommendation catch block called." +e);
		}
		//return jobData;

	}
	
	
	
	private void getRecommendedJobsData(long userID, BasicDBList jobList) throws UnknownHostException, MongoException{
		
		
		System.out.println("####################----------Get Recommended Job Data Method Called with  User " + userID + " and Jobs "+ jobList +"-------##############");
		System.out.println(jobList);
		//System.out.println("####################-----JOB LIST ABOVE-------CALLED PRIVATE-------##############");
		
//		ArrayList<JobBean> jobData = new ArrayList<JobBean>();
		
		
		DBCollection dbobj=mongodb.getCollection("Jobs");		
		//BasicDBObject whereQuery = new BasicDBObject();
		BasicDBObject inQuery = new BasicDBObject();
		DBCollection recommendedJobDetail=mongodb1.getCollection("RecommendedJobsforUsers");		
		
//		for(Object jobId : jobList)
//		{
//			System.out.println("Here JOb ID is"+ jobId);
//			whereQuery.put("JobID", 1);
//			 DBCursor cursor = dbobj.find(whereQuery);
//			 System.out.println(cursor.next().toMap());
//			 whereQuery.clear();
//		}
//		
		inQuery.put("JobID", new BasicDBObject("$in", jobList));
		 DBCursor cursor = dbobj.find(inQuery);
		 
		 Map temp;
		 ArrayList<Integer> requiredSkills = new ArrayList<Integer>();
		//inQuery.put("skillID", new BasicDBObject("$in", skillID));
		 BasicDBObject jobDocument = new BasicDBObject();
		 BasicDBObject userjobsDocument = new BasicDBObject();
		 BasicDBList recommendedSkills = new BasicDBList();
		 BasicDBList recommendedJobs = new BasicDBList();
//		 recommendedSkills.add("Hi");
//		 recommendedSkills.add("Hello");
//		 recommendedSkills.add("HiHello");
		 BasicDBObject tempDoc;
		 ExistingSkills existingSkills = new ExistingSkills();			   
		 ArrayList<Integer> existingSkillNames = existingSkills.getExistingSkillsList(userID);
		// ArrayList<Long> requiredSkills = new ArrayList<Long>();
		    System.out.println("####################----------Existing Skill Count for User  "+ userID+ " = " + existingSkillNames.size());
		   
		    while(cursor.hasNext()) {
//		    	recommendedSkills.add((String)cursor.next().get("skillName"));
		    	System.out.println("####################------------HERE-------##############");
		    	temp = cursor.next().toMap();
		    	requiredSkills = getRequiredSkillsForJob((Map<String, Integer>)temp.get("RequiredSkills"));
		    	
		    	jobDocument.put("JobID", temp.get("JobID"));
		    	jobDocument.put("JobTitle", temp.get("JobTitle"));
		    	jobDocument.put("CompanyName", temp.get("CompanyName"));
		    	jobDocument.put("City", temp.get("City"));
		    	jobDocument.put("State", temp.get("State"));
		    	jobDocument.put("Percentage Match", percentageMatch(requiredSkills, existingSkillNames));
		    	jobDocument.put("Recommended Skills", getRecommendedSkillsName(requiredSkills, existingSkillNames));
		    	tempDoc =jobDocument;
		    	
		    	recommendedJobs.add(tempDoc);
		    	//jobDocument.clear();
		    	System.out.println(tempDoc);
		    	jobDocument = new BasicDBObject();
		    	System.out.println("RequiredSkills" +requiredSkills);
		    	System.out.println("ExistingSkills" +existingSkillNames);
		    	System.out.println("####################------------Percentage Match-------##############");
		    	System.out.println(percentageMatch(requiredSkills, existingSkillNames));
		    }
		    
		    userjobsDocument.put("user_id", userID);
		    userjobsDocument.put("recommendedJobs", recommendedJobs);
		    recommendedJobDetail.insert(userjobsDocument);
		
//		 return jobData;
		
	}
	
	
	
	
	public int percentageMatch(ArrayList<Integer> requiredSkills, ArrayList<Integer> existingSkills)
	{
		int count =0;
		int totalCount = requiredSkills.size();
		for(Integer i: requiredSkills)
		{
			System.out.println("SkillMatch "+ i);
			boolean isExist = existingSkills.contains(i);
			
			if(isExist)
			{
				System.out.println(isExist);
				count++;
			}
			
		}
		
		System.out.println("fhsdjkfhshfdjkshdkf "+count);
		System.out.println("fsdfsdfsfsdfdzf "+totalCount);
		int answer = ((count*100)/totalCount);
		
		return answer;
	}
	
	
	
	public BasicDBList getRecommendedSkillsName(ArrayList<Integer> requiredSkills, ArrayList<Integer> existingSkills) throws UnknownHostException, MongoException
	{
		
		int count =0;
		int totalCount = requiredSkills.size();
		BasicDBList recommendeSkills = new BasicDBList();
		for(Integer i: requiredSkills)
		{
			System.out.println("SkillMatch "+ i);
			boolean isExist = existingSkills.contains(i);
			
			if(!isExist)
			{
				
				recommendeSkills.add(i);
			}
			
			
		}
	//	MongoDBConnection mongodb=new MongoDBConnection();
		//DBCollection dbobj=mongodb.getCollection("SkillsRecommendations");
		//BasicDBObject whereQuery = new BasicDBObject();
		
		BasicDBList answer = getRecommendedSkillsNameforUSer(recommendeSkills);
		
//		ArrayList<Long> skillID = new ArrayList<Long>();
//		whereQuery.put("user_id", userID);
//		DBCursor cursor = dbobj.find(whereQuery);
//		while(cursor.hasNext()) {
//			skillID= (ArrayList<Long>)cursor.next().get("recommendedSkills");
//		    }
		
		
		return answer;
	}
	
	
	
	public BasicDBList getRecommendedSkillsNameforUSer(BasicDBList skillID) throws UnknownHostException, MongoException
	{
		
		System.out.println("This Called");
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("Skills");
		BasicDBObject inQuery = new BasicDBObject();
		BasicDBList recommendedSkills = new BasicDBList();
		inQuery.put("skillId", new BasicDBObject("$in", skillID));
		    DBCursor cursor = dbobj.find(inQuery);
		    while(cursor.hasNext()) {
		    	recommendedSkills.add((String)cursor.next().get("skill"));
		    }
		
		 return recommendedSkills;
	}
	
	
	
	public ArrayList<Integer> getRecommendedSkills(long userID) throws UnknownHostException, MongoException
	{
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("SkillsRecommendations");
		BasicDBObject whereQuery = new BasicDBObject();
		ArrayList<Integer> skillID = new ArrayList<Integer>();
		whereQuery.put("user_id", userID);
		DBCursor cursor = dbobj.find(whereQuery);
		while(cursor.hasNext()) {
			skillID= (ArrayList<Integer>)cursor.next().get("recommendedSkills");
		    }
		
		
		return skillID;
	}
	
	public ArrayList<Integer> getRequiredSkillsForJob(Map<String, Integer> inputJobSkills){
		
		ArrayList<Integer> requiredSkills = new ArrayList<Integer>();
		
		for(Map.Entry<String, Integer> entry : inputJobSkills.entrySet()){
			
			requiredSkills.add(Integer.valueOf(String.valueOf(entry.getKey())));
			
		}
		
		
		return requiredSkills;
		
		
		
	}
	
	

}
