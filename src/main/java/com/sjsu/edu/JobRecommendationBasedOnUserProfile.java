package job.recommendations;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.sjsu.edu.recommendations.MongoDBConnection;

public class JobRecommendationBasedOnUserProfile {

	public void addUserProfile() {

		DataModel dbm;
		try {
			writeCSVforJobRecommmendation();
			dbm = new FileDataModel(
					new File(
							"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\SkillPreferencces.csv"));

			HashMap<Long, List<Long>> recommendedJobs = new HashMap<Long, List<Long>>();

			for (LongPrimitiveIterator users = dbm.getUserIDs(); users
					.hasNext();) {
				long userId = users.nextLong();
				System.out.println("Processing User " + userId);

				InputUserDataToNewCSVFile(userId);
				DataModel newDataModel = new FileDataModel(
						new File(
								"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\JobRecommendationInputFilewithUser.csv"));

				UserSimilarity jobSimilarity = new PearsonCorrelationSimilarity(
						newDataModel);
				// System.out.println(userSimilarity.userSimilarity(10018450,
				// 1));
				// UserNeighborhood jobneighborhood = new
				// ThresholdUserNeighborhood(0.0, jobSimilarity, dbm);
				NearestNUserNeighborhood jobneighborhood = new NearestNUserNeighborhood(
						5, jobSimilarity, newDataModel);

				// System.out.println(neighborhood.getUserNeighborhood(10018450));
				GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(
						newDataModel, jobneighborhood, jobSimilarity);
				// Recommender cachingRecommender = new
				// CachingRecommender(recommender);
				// System.out.println("----------------------------------");
				// System.out.println(Long.valueOf("2"));
				// List<RecommendedItem> recommendations =
				// recommender.recommend(1119990, 2);
				// System.out.println(recommendations);
				// for (RecommendedItem recommendation : recommendations) {
				// System.out.println("Hi");
				// System.out.println(recommendation);
				// }
				// ArrayList<Long> recommendations;
				long[] recommendations;

				// System.out.println(dbm.getUserIDs().hasNext());
				ArrayList<Long> userJobs = new ArrayList<Long>();
				ArrayList<Long> userJobsInsert;
				ArrayList<Long> updatedJobList;

				recommendations = recommender.mostSimilarUserIDs(userId, 50);
				System.out.println(" All Recommendartions for user " + userId
						+ " = " + recommendations);
				for (long recommendation : recommendations) {
					// System.out.println("Check Here");
					// System.out.println(recommendation.getItemID());
					userJobs.add(recommendation);
					// System.out.println(userJobs);
				}
				System.out.println("List of Recommended Job for user "
						+ userJobs);
				updatedJobList = filterAppliedJobs(userId, userJobs);
				userJobsInsert = new ArrayList<Long>(updatedJobList);
				System.out.println("List of filtered job for user "
						+ userJobsInsert);
				recommendedJobs.put(userId, userJobsInsert);
				userJobs.clear();

			}
			System.out.println(recommendedJobs);
			InsertSkill skills = new InsertSkill();
			skills.insertJobs(recommendedJobs);

			for (LongPrimitiveIterator users = dbm.getUserIDs(); users
					.hasNext();) {
				long userId = users.nextLong();
				System.out.println("Recommended Skills for user " + userId
						+ " = " + skills.getRecommendedSkills(userId));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// recommendations = recommender.recommend(userId, 10);
		// System.out.println("All Recommendartions "+recommendations);
		// for (RecommendedItem recommendation : recommendations) {
		// System.out.println("Check Here");
		// System.out.println(recommendation.getItemID());
		// userSkills.add(recommendation.getItemID());
		// System.out.println(userSkills);
		// }
		// userSkillsInsert = new ArrayList<Long>(userSkills);
		// recommendedSkills.put(userId, userSkillsInsert);
		// userSkills.clear();

	}

	// System.out.println(recommendedSkills);
	// InsertSkill skills = new InsertSkill();
	// skills.insertSkills(recommendedSkills);
	//
	// for(LongPrimitiveIterator users = dbm.getUserIDs(); users.hasNext();) {
	// long userId=users.nextLong();
	// System.out.println("Hello  "+skills.getRecommendedSkills(userId));
	// }

	// public void addUserSkillstoFile(long userId){
	//
	//
	// String inputFilePath =
	// "C:\\Users\\Bharat\\Downloads\JobRecommendationInputFile.csv";
	// String source_file =
	// "src/main/resources/output/movies-genres-processed.csv";
	// File src_file = new File(source_file);
	// String dest_file_path = file_path + "movies-genres-processed-.csv";
	// File dest_file = new File(dest_file_path);
	//
	// FileUtils.copyFile(src_file,dest_file);
	// CSVFormat outputFormat =
	// CSVFormat.DEFAULT.withDelimiter(',').withRecordSeparator(NL);
	// FileWriter fileWriter = new FileWriter(file, true);
	//
	// CSVPrinter printer = new CSVPrinter(fileWriter,outputFormat);
	//
	// for (int i = 0; i < movie_genre_user.length; i++) {
	// printer.print(userID);
	// printer.print(movie_genre_user[i]);
	// printer.print(1);
	// printer.println();
	// }
	// printer.close();
	// }

	public void CopyPreviousDataToNewCSVFile() throws IOException {

		System.out.println("Called copy previous data to new csv file");
		FileWriter userJobRecommendation = new FileWriter(
				"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\JobRecommendationInputFilewithUser.csv",
				false);
		FileReader jobRecommendationInputFile = new FileReader(
				"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\JobRecommendationInputFile.csv");

		try {
			int count = jobRecommendationInputFile.read();
			while (count != -1) {
				userJobRecommendation.write(count);
				count = jobRecommendationInputFile.read();
			}
			userJobRecommendation.close();
			jobRecommendationInputFile.close();
			System.out
					.println("Called copy previous data to new csv file Done.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void InputUserDataToNewCSVFile(long userId) throws IOException {

		System.out.println("Called Input user data to new csv file for User "
				+ userId);
		CopyPreviousDataToNewCSVFile();

		FileWriter userJobRecommendation = new FileWriter(
				"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\JobRecommendationInputFilewithUser.csv",
				true);
		CSVPrinter csvFilePrinter = null;
		HashMap<Integer, Integer> userSkills = getUserSkills(userId);

		try {
			csvFilePrinter = new CSVPrinter(userJobRecommendation,
					new CSVStrategy(',', '\n', ':'));

			for (Map.Entry<Integer, Integer> entry : userSkills.entrySet()) {
				csvFilePrinter.print(String.valueOf((int) userId));
				csvFilePrinter.print("" + entry.getKey());
				csvFilePrinter.print("" + entry.getValue());
				csvFilePrinter.println();
			}
			System.out.println("Latest created Skill Set  Map");
			System.out.println(userSkills);
			System.out.println("After Called Input user data to new csv file.");
			userJobRecommendation.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public HashMap<Integer, Integer> getUserSkills(long userID)
			throws UnknownHostException, MongoException {

		System.out.println("get user skills called for User " + userID);
		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("ratings");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList skillID = new BasicDBList();
		whereQuery.put("userId", userID);
		DBCursor cursor = dbobj.find(whereQuery);
		System.out.println("Number of SkillsFetched For User " + userID + " = "
				+ cursor.count());
		HashMap<Integer, Integer> userSkill = new HashMap<Integer, Integer>();
		DBObject userPreferences;
		while (cursor.hasNext()) {
			userPreferences = cursor.next();
			userPreferences.get("skillId");
			userPreferences.get("proficiency");
			userSkill.put((Integer) userPreferences.get("skillId"),
					(Integer) userPreferences.get("proficiency"));
			// System.out.println(userPreferences.get("item_id"));
			// System.out.println(userPreferences.get("preference"));
		}

		System.out.println("get user skills Done. UserSkill Map created for "
				+ userID + " = " + userSkill);
		return userSkill;

	}

	public void writeCSVforJobRecommmendation() throws IOException {

		FileWriter userJobRecommendation = new FileWriter(
				"C:\\VARUN\\D\\SPRING_2016\\295B\\Project_Coding\\JobRecommendationInputFile.csv",
				false);
		CSVPrinter csvFilePrinter = null;
		// HashMap<Long,Long> userSkills = getUserSkills(userId);

		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("Jobs");
		DBCursor cursor = dbobj.find();

		System.out
				.println("Total Number of Jobs Retrieved from JobsCollection");
		System.out.println(cursor.count());

		try {
			csvFilePrinter = new CSVPrinter(userJobRecommendation,
					new CSVStrategy(',', '\n', ':'));
			System.out
					.println("Writing csv file for Job Recommendation from JobsCollection");
			while (cursor.hasNext()) {
				DBObject jobSkillDetail = cursor.next();
				int jobID = (Integer) jobSkillDetail.get("JobID");
				Map<Long, Long> requiredSkills = ((BasicDBObject) jobSkillDetail
						.get("RequiredSkills")).toMap();
				System.out
						.println("#############Required Skillsfor particular Job #############3333");
				System.out.println(jobID + "------" + requiredSkills);
				for (Map.Entry<Long, Long> entry : requiredSkills.entrySet()) {

					csvFilePrinter.print(String.valueOf(jobID));
					csvFilePrinter.print("" + entry.getKey());
					csvFilePrinter.print("" + entry.getValue());
					csvFilePrinter.println();
				}
			}
			System.out
					.println("Writing csv file for Job Recommendation from JobsCollection Done.");

			userJobRecommendation.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public ArrayList<Long> filterAppliedJobs(long userId,
			ArrayList<Long> recommendedJobs) throws UnknownHostException,
			MongoException {

		MongoDBConnection mongodb = new MongoDBConnection();
		DBCollection dbobj = mongodb.getCollection("AppliedJobs");
		BasicDBObject whereQuery = new BasicDBObject();
		BasicDBList jobList = new BasicDBList();
		whereQuery.put("user_id", userId);
		DBCursor cursor = dbobj.find(whereQuery);
		while (cursor.hasNext()) {
			jobList = (BasicDBList) cursor.next().get("appliedJobs");
		}

		for (Object jobId : jobList) {

			recommendedJobs.remove(jobId);

		}
		return recommendedJobs;
	}

}
