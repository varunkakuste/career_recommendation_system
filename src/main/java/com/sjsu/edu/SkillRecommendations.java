package job.recommendations;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sjsu.edu.recommendations.MongoDBConnection;

public class SkillRecommendations {

	public void generateSkillsRecs() throws UnknownHostException, TasteException {
		// TODO Auto-generated method stub

 
		        System.out.println( "Hello World!" );
		    
		        
		      //  MongoDBDataModel dbm = new MongoDBDataModel("ds019970.mlab.com", 19970, "career_recommendation", "Ratings", true, true, null,"Bharat","temp","user_id","item_id","preference","Ratings");
		     //   MongoDBDataModel dbm = new MongoDBDataModel("localhost", 27017, "local", "ratings1", true, true, null);//,"user_id","item_id","preference","ratings1");
		       // SVDRecommender svd = new SVDRecommender(dbm, new ALSWRFactorizer(dbm, 1, 0.05f, 5));
		       // List<RecommendedItem> recommendations = svd.recommend(1, 10);
			//	for (RecommendedItem recommendedItem : recommendations) {
			//		System.out.println(recommendations);
			//	}	   
		       
		    	try {
		    		writeCSVforSkillRecommmendation();
		    		//DataModel dbm = new FileDataModel(new File("C:\\Users\\Bharat\\Downloads\\ratings.csv"));
		    		DataModel dbm = new FileDataModel(new File("C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\SkillPreferencces.csv"));
					
				
		        
				//UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dbm);
				 UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dbm);
		      	//System.out.println(userSimilarity.userSimilarity(10018450, 1));
				UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.0, userSimilarity, dbm);
				  NearestNUserNeighborhood userneighborhood1 = new NearestNUserNeighborhood(2, userSimilarity, dbm);
			      
//				System.out.println(neighborhood.getUserNeighborhood(10018450));
				Recommender recommender = new GenericUserBasedRecommender(dbm, userneighborhood1, userSimilarity);
				//Recommender cachingRecommender = new CachingRecommender(recommender);
				System.out.println("----------------------------------");
				System.out.println(Long.valueOf("2"));
					// List<RecommendedItem> recommendations = recommender.recommend(1119990, 2);
				//	 System.out.println(recommendations);
				//	 for (RecommendedItem recommendation : recommendations) {
			      //  	 System.out.println("Hi");
			       //     System.out.println(recommendation);
					// }
					 List<RecommendedItem> recommendations;
					 HashMap<Long, List<Long>> recommendedSkills = new HashMap<Long, List<Long>>();
					 System.out.println(dbm.getUserIDs().hasNext());
					 ArrayList<Long> userSkills = new ArrayList<Long>();
					 ArrayList<Long> userSkillsInsert;
					 
					 
			            for(LongPrimitiveIterator users = dbm.getUserIDs(); users.hasNext();) {
			         	   long userId=users.nextLong();
			         	   System.out.println(userId);
			         	  recommendations = recommender.recommend(userId, 10);
			         	  System.out.println("All Recommendartions "+recommendations);
			         	 for (RecommendedItem recommendation : recommendations) {
						        	 System.out.println("Check Here");
						        	System.out.println(recommendation.getItemID());
						        	 userSkills.add(recommendation.getItemID());
						        	 System.out.println(userSkills);
								 }
			         	userSkillsInsert =  new ArrayList<Long>(userSkills);
			         	 recommendedSkills.put(userId, userSkillsInsert);
			         	 userSkills.clear();
			         	 
			         	}
					System.out.println(recommendedSkills);
					InsertSkill skills = new InsertSkill();
					skills.insertSkills(recommendedSkills);
					
					for(LongPrimitiveIterator users = dbm.getUserIDs(); users.hasNext();) {
			         	   long userId=users.nextLong();
			         System.out.println("Hello  "+skills.getRecommendedSkills(userId));	   
					}
	
		    	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void writeCSVforSkillRecommmendation() throws IOException
	{
		
		FileWriter userJobRecommendation = new FileWriter("C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\SkillPreferencces.csv", false);
		CSVPrinter csvFilePrinter = null;
		//HashMap<Long,Long> userSkills = getUserSkills(userId);
		
		MongoDBConnection mongodb=new MongoDBConnection();
		DBCollection dbobj=mongodb.getCollection("ratings");
		DBCursor cursor = dbobj.find();
		
		System.out.println("Total Number of User Skill Records Retrieved from Skill Collection");
		System.out.println(cursor.count());

		try {
			csvFilePrinter = new CSVPrinter(userJobRecommendation, new CSVStrategy(',', '\n', ':'));
			System.out.println("Writing csv file for Skill Recommendation from Skills Collection");
			while(cursor.hasNext())
			{
				DBObject userSkillDetail = cursor.next();
				int userID = (Integer)userSkillDetail.get("userId");
				int itemID = (Integer)userSkillDetail.get("skillId");
				int rating = (Integer)userSkillDetail.get("proficiency");
				
				System.out.println("#############User and Skill #############3333");
				System.out.println(userID +"------"+itemID);
			
				csvFilePrinter.print(String.valueOf((int) userID));
				csvFilePrinter.print(""+itemID);
				csvFilePrinter.print(""+rating);
				csvFilePrinter.println();
				
			}
			System.out.println("Writing csv file for Skill Recommendation from Skills Collection Done.");
			
			userJobRecommendation.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
}
