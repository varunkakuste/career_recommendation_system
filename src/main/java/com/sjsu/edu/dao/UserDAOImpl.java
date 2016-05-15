package com.sjsu.edu.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sjsu.edu.beans.DashBoardBean;
import com.sjsu.edu.beans.JobRecommendationBean;
import com.sjsu.edu.beans.LoginForm;
import com.sjsu.edu.beans.SignupForm;
import com.sjsu.edu.beans.SkillsProficiencyBean;
import com.sjsu.edu.beans.UserForm;

public class UserDAOImpl implements IUserDAO {

	@Override
	public UserForm login(LoginForm loginForm) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		UserForm userDetails = null;
		SignupForm signupDetails = null;
		int userId = 0;
		try {
			dbobj = mongodb.getCollection("Users");
			objQuery.put("email", loginForm.getEmailLogin());
			objQuery.put("password", loginForm.getPasswordLogin());
			cursor = dbobj.find(objQuery);
			if (cursor.hasNext()) {
				userDetails = new UserForm();
				signupDetails = new SignupForm();
				resultObj = cursor.next();
				userId = Integer.valueOf(String.valueOf(resultObj.get("userId")));
				signupDetails.setFirst_name(String.valueOf(resultObj
						.get("first_name")));
				signupDetails.setLast_name(String.valueOf(resultObj
						.get("last_name")));
				signupDetails.setEmail(String.valueOf(resultObj.get("email")));
				userDetails.setSignupForm(signupDetails);
				userDetails.setUserId(userId);
				userDetails
						.setAddress(String.valueOf(resultObj.get("address")));
				userDetails.setCity(String.valueOf(resultObj.get("city")));
				userDetails.setState(String.valueOf(resultObj.get("state")));
				userDetails
						.setCountry(String.valueOf(resultObj.get("country")));
				userDetails.setGender(String.valueOf(resultObj.get("gender")));
				userDetails.setLocation(String.valueOf(resultObj
						.get("location")));
				userDetails.setDashBoardBean(getDashBoardData(userId));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userDetails;
	}

	public int getNextUserSequence(String name) {
		int result = 0;
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = null;
		try {
			dbobj = mongodb.getCollection("counters");
			DBObject query = new BasicDBObject("_id", name);
			DBObject update = new BasicDBObject();
			update.put("$inc", new BasicDBObject("seq", 1));
			dbobj.findAndModify(query, update);
			cursor = dbobj.find();
			while (cursor.hasNext()) {
				objQuery = cursor.next();
				if (objQuery.get("_id").equals(name)) {
					result = Integer.valueOf(objQuery.get("seq").toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public boolean createProfile(UserForm userForm) {
		boolean result = false;
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		try {
			dbobj = mongodb.getCollection("Users");
			BasicDBObject doc = new BasicDBObject("userId",
					userForm.getUserId())
					.append("first_name",
							userForm.getSignupForm().getFirst_name())
					.append("last_name",
							userForm.getSignupForm().getLast_name())
					.append("email", userForm.getSignupForm().getEmail())
					.append("password", userForm.getSignupForm().getPassword())
					.append("address", userForm.getAddress())
					.append("city", userForm.getCity())
					.append("state", userForm.getState())
					.append("country", userForm.getCountry())
					.append("gender", userForm.getGender())
					.append("location", userForm.getLocation());
			dbobj.insert(doc);

			dbobj = null;
			dbobj = mongodb.getCollection("ratings");
			for (SkillsProficiencyBean bean : userForm.getSkillsProficiency()) {
				doc = new BasicDBObject("userId", userForm.getUserId())
						.append("skillId", bean.getSkillId())
						.append("proficiency", bean.getProficiency())
						.append("skill", bean.getSkill());
				dbobj.insert(doc);
			}
			result = true;
		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	@Override
	public List<String> getRecommendedSkills(int userID) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList skillIdList = null;
		List<String> recommendedSkillsList = null;
		try {
			dbobj = mongodb.getCollection("SkillsRecommendations");
			objQuery.put("user_id", userID);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				skillIdList = (BasicDBList) cursor.next().get(
						"recommendedSkills");
				if (skillIdList != null && !skillIdList.isEmpty()) {
					break;
				}
			}
			recommendedSkillsList = getRecommendedSkillsName(skillIdList);
		} catch (Exception e) {
			System.out.println(e);
		}
		return recommendedSkillsList;
	}
	
	private List<String> getRecommendedSkillsName(BasicDBList skillIDList) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		List<String> recommendedSkillsList = new ArrayList<String>();
		try {
			dbobj = mongodb.getCollection("Skills");
			objQuery.put("skillId", new BasicDBObject("$in", skillIDList));
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				recommendedSkillsList.add((String) cursor.next().get("skill"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recommendedSkillsList;
	}

	@Override
	public Map<String, Integer> getExistingSkills(int userID) {
		Map<String, Integer> existingSkills = new HashMap<String, Integer>();
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		try {
			dbobj = mongodb.getCollection("ratings");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				existingSkills.put(String.valueOf(resultObj.get("skill")),
						Integer.valueOf(String.valueOf(resultObj
								.get("proficiency"))));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return existingSkills;
	}
	
	@Override
	public Map<Integer, Integer> getExistingSkillIdAndProf(int userID) {
		Map<Integer, Integer> existingSkills = new HashMap<Integer, Integer>();
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		try {
			dbobj = mongodb.getCollection("ratings");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				existingSkills.put(Integer.valueOf(String.valueOf(resultObj.get("skillId"))), Integer.valueOf(String.valueOf(resultObj.get("proficiency"))));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return existingSkills;
	}

	@Override
	public DashBoardBean getDashBoardData(int userID) {
		DashBoardBean dashBoard = null;
		try {
			dashBoard = new DashBoardBean();
			int jobCount = getRecommendedJobCount(userID);
			if(jobCount == 0) {
				jobCount = 10;
			}
			int skillCount = getRecommendedSkillCount(userID);
			int appliedJobcount = getAppliedJobCount(userID);
			int savedJobCount = getSavedJobCount(userID);
			dashBoard.setNoOfRecommendedJobs(jobCount);
			dashBoard.setNoOfRecommendedSkills(skillCount);
			dashBoard.setNoOfAppliedJobs(appliedJobcount);
			dashBoard.setNoOfSavedJobs(savedJobCount);
		} catch (Exception e) {
			System.out.println(e);
		}
		return dashBoard;
	}

	private int getRecommendedJobCount(int userId) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		BasicDBList jobCount = new BasicDBList();
		try {
			dbobj = mongodb.getCollection("RecommendedJobsforUsers");
			objQuery.put("user_id", userId);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				jobCount = (BasicDBList) resultObj.get("recommendedJobs");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return jobCount.size();
	}

	private int getRecommendedSkillCount(int userId) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		BasicDBList skillCount = new BasicDBList();
		try {
			dbobj = mongodb.getCollection("SkillsRecommendations");
			objQuery.put("user_id", userId);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				skillCount = (BasicDBList) resultObj.get("recommendedSkills");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return skillCount.size();
	}

	private int getSavedJobCount(int userId) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		BasicDBList savedJobCount = new BasicDBList();
		try {
			dbobj = mongodb.getCollection("SavedJobs");
			objQuery.put("userId", userId);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				savedJobCount = (BasicDBList) resultObj.get("savedJobs");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return savedJobCount.size();
	}

	private int getAppliedJobCount(int userId) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject objQuery = new BasicDBObject();
		DBObject resultObj = null;
		BasicDBList appliedJobCount = new BasicDBList();
		try {
			dbobj = mongodb.getCollection("AppliedJobs");
			objQuery.put("userId", userId);
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				appliedJobCount = (BasicDBList) resultObj.get("Jobs_applied");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return appliedJobCount.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobRecommendationBean> getDefaultJobs(List<SkillsProficiencyBean> skillsProfList) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		List<JobRecommendationBean> resultList = new ArrayList<JobRecommendationBean>();
		JobRecommendationBean jobBean = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList skillIDList = null;
		
		try {
			Collections.sort(skillsProfList, new Comparator<SkillsProficiencyBean>() {
			    @Override
			    public int compare(SkillsProficiencyBean a, SkillsProficiencyBean b) {
		    		if (a.getProficiency() == b.getProficiency())
		    			return 0;
		    		else if (b.getProficiency() > a.getProficiency())
		    			return 1;
		    		else
		    			return -1;
			    }
			});
			
			dbobj = mongodb.getCollection("Jobs");
			objQuery.put("RequiredSkills."+skillsProfList.get(0).getSkillId(), skillsProfList.get(0).getProficiency());
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				jobBean = new JobRecommendationBean();
				jobBean.setJobId(Integer.valueOf(String.valueOf(resultObj.get("JobID"))));
				jobBean.setCompanyName(String.valueOf(resultObj.get("CompanyName")));
				jobBean.setPosition(String.valueOf(resultObj.get("JobTitle")));
				jobBean.setLocation(String.valueOf(resultObj.get("City")) + ", " + String.valueOf(resultObj.get("State")));
				skillIDList = new BasicDBList();
				Map<Integer,Integer> requiredSkills = ((BasicDBObject)resultObj.get("RequiredSkills")).toMap();
				for(Map.Entry<Integer, Integer> entry: requiredSkills.entrySet()) {
					skillIDList.add(Integer.valueOf(String.valueOf(entry.getKey())));
				}
				jobBean.setSkills(getRecommendedSkillsName(skillIDList));
				if(resultList.size() >= 10) {
					break;
				} else {
					resultList.add(jobBean);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;
	}

	@Override
	public List<JobRecommendationBean> getRecommendedJobs(int userID) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		List<JobRecommendationBean> resultList = new ArrayList<JobRecommendationBean>();
		JobRecommendationBean jobBean = null;
		DBObject objQuery = new BasicDBObject();
		List<String> recommendedSkills = null;
		try {
			dbobj = mongodb.getCollection("RecommendedJobsforUsers");
			objQuery.put("user_id", Long.valueOf(userID));
			cursor = dbobj.find(objQuery);
			while (cursor.hasNext()) {
				resultObj = cursor.next();
				BasicDBList jobList = ((BasicDBList)resultObj.get("recommendedJobs"));
				for (int i = 0; i < jobList.size(); ++i) {
		            Object val = jobList.get(i);
		            if (val instanceof BasicDBObject) {
		            	jobBean = new JobRecommendationBean();
		            	jobBean.setJobId(Integer.valueOf(String.valueOf(((BasicDBObject) val).get("JobID"))));
						jobBean.setCompanyName(String.valueOf(((BasicDBObject) val).get("CompanyName")));
						jobBean.setPosition(String.valueOf(((BasicDBObject) val).get("JobTitle")));
						jobBean.setLocation(String.valueOf(((BasicDBObject) val).get("City")) + ", " + String.valueOf(String.valueOf(((BasicDBObject) val).get("State"))));
						jobBean.setPercentageMatched(Integer.valueOf(String.valueOf(((BasicDBObject) val).get("Percentage Match"))));
						String[] recommendedSkillsArr = (((BasicDBObject) val).getString("Recommended Skills").toString()).split(",");
						recommendedSkills = new ArrayList<String>();
						for(String str: recommendedSkillsArr) {
							str = str.replaceAll("\"", "");
							str = str.replace("[", "");
							str = str.replace("]", "");
							recommendedSkills.add(str.trim());
						}
						jobBean.setSkills(recommendedSkills);
						if(resultList.size() >= 10) {
							break;
						} else {
							resultList.add(jobBean);
						}
		            }
		        }
				if(resultList.size() >= 10) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;
	}
	
	@Override
	public boolean applyJob(int userID, int jobId){
		boolean result = false;
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList jobList = null;
		BasicDBObject match = null;
		BasicDBObject update = null;
		try {
			dbobj = mongodb.getCollection("AppliedJobs");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			if (cursor.hasNext()) {
				resultObj = cursor.next();
				jobList = ((BasicDBList)resultObj.get("Jobs_applied"));
			}
			if(jobList == null || jobList.isEmpty()) {
				jobList = new BasicDBList();
				jobList.add(jobId);
				BasicDBObject doc = new BasicDBObject("userId", userID).append("Jobs_applied", jobList);
				dbobj.insert(doc);
			} else {
				match = new BasicDBObject("userId", userID); // to match your document
				update = new BasicDBObject("Jobs_applied", jobId);
				dbobj.update(match, new BasicDBObject("$push", update));
			}
			
			dbobj = mongodb.getCollection("RecommendedJobsforUsers");
			match = new BasicDBObject("user_id", userID); // to match your document
			update = new BasicDBObject("recommendedJobs", new BasicDBObject("JobID", jobId));
			dbobj.update(match, new BasicDBObject("$pull", update));
			result = true;
		} catch(Exception expt) {
			System.out.println(expt);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobRecommendationBean> getAppliedJobs(int userID) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		List<JobRecommendationBean> resultList = new ArrayList<JobRecommendationBean>();
		JobRecommendationBean jobBean = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList skillIDList = null;
		BasicDBList jobList = null;
		
		try {
			dbobj = mongodb.getCollection("AppliedJobs");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			if (cursor.hasNext()) {
				resultObj = cursor.next();
				jobList = ((BasicDBList)resultObj.get("Jobs_applied"));
			}
			if(jobList != null && !jobList.isEmpty()) {
				objQuery = new BasicDBObject();
				dbobj = null;
				cursor = null;
				dbobj = mongodb.getCollection("Jobs");
				objQuery.put("JobID", new BasicDBObject("$in", jobList));
				cursor = dbobj.find(objQuery);
				while (cursor.hasNext()) {
					resultObj = cursor.next();
					jobBean = new JobRecommendationBean();
	            	jobBean.setJobId(Integer.valueOf(String.valueOf(resultObj.get("JobID"))));
					jobBean.setCompanyName(String.valueOf(resultObj.get("CompanyName")));
					jobBean.setPosition(String.valueOf(resultObj.get("JobTitle")));
					jobBean.setLocation(String.valueOf(resultObj.get("City")) + ", " + String.valueOf(String.valueOf(resultObj.get("State"))));
					skillIDList = new BasicDBList();
					Map<Integer,Integer> requiredSkills = ((BasicDBObject)resultObj.get("RequiredSkills")).toMap();
					for(Map.Entry<Integer, Integer> entry: requiredSkills.entrySet()) {
						skillIDList.add(Integer.valueOf(String.valueOf(entry.getKey())));
					}
					jobBean.setSkills(getRecommendedSkillsName(skillIDList));
					resultList.add(jobBean);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;
	}
	
	@Override
	public boolean saveJob(int userID, int jobId) {
		boolean result = false;
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList jobList = null;
		BasicDBObject match = null;
		BasicDBObject update = null;
		try {
			dbobj = mongodb.getCollection("SavedJobs");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			if (cursor.hasNext()) {
				resultObj = cursor.next();
				jobList = ((BasicDBList)resultObj.get("savedJobs"));
			}
			if(jobList == null || jobList.isEmpty()) {
				jobList = new BasicDBList();
				jobList.add(jobId);
				BasicDBObject doc = new BasicDBObject("userId", userID).append("savedJobs", jobList);
				dbobj.insert(doc);
			} else {
				match = new BasicDBObject("userId", userID); // to match your document
				update = new BasicDBObject("savedJobs", jobId);
				dbobj.update(match, new BasicDBObject("$push", update));
			}
			result = true;
		} catch(Exception expt) {
			System.out.println(expt);
		}
		return result;
	}
	
	@Override
	public List<JobRecommendationBean> getSavedJobs(int userID) {
		MongoDB mongodb = new MongoDB();
		DBCollection dbobj = null;
		DBCursor cursor = null;
		DBObject resultObj = null;
		List<JobRecommendationBean> resultList = new ArrayList<JobRecommendationBean>();
		JobRecommendationBean jobBean = null;
		DBObject objQuery = new BasicDBObject();
		BasicDBList jobList = null;
		List<String> recommendedSkills = null;
		
		try {
			dbobj = mongodb.getCollection("SavedJobs");
			objQuery.put("userId", userID);
			cursor = dbobj.find(objQuery);
			if (cursor.hasNext()) {
				resultObj = cursor.next();
				jobList = ((BasicDBList)resultObj.get("savedJobs"));
			}
			if(jobList != null && !jobList.isEmpty()) {
				objQuery = new BasicDBObject();
				dbobj = null;
				cursor = null;
				dbobj = mongodb.getCollection("RecommendedJobsforUsers");
				objQuery.put("user_id", Long.valueOf(userID));
				cursor = dbobj.find(objQuery);
				while (cursor.hasNext()) {
					resultObj = cursor.next();
					BasicDBList jobRecommendedList = ((BasicDBList)resultObj.get("recommendedJobs"));
					for (int i = 0; i < jobRecommendedList.size(); ++i) {
			            Object val = jobRecommendedList.get(i);
			            if (val instanceof BasicDBObject) {
			            	
			            	if(jobList.contains(Integer.valueOf(String.valueOf(((BasicDBObject) val).get("JobID"))))) {
			            		jobBean = new JobRecommendationBean();
				            	jobBean.setJobId(Integer.valueOf(String.valueOf(((BasicDBObject) val).get("JobID"))));
								jobBean.setCompanyName(String.valueOf(((BasicDBObject) val).get("CompanyName")));
								jobBean.setPosition(String.valueOf(((BasicDBObject) val).get("JobTitle")));
								jobBean.setLocation(String.valueOf(((BasicDBObject) val).get("City")) + ", " + String.valueOf(String.valueOf(((BasicDBObject) val).get("State"))));
								jobBean.setPercentageMatched(Integer.valueOf(String.valueOf(((BasicDBObject) val).get("Percentage Match"))));
								String[] recommendedSkillsArr = (((BasicDBObject) val).getString("Recommended Skills").toString()).split(",");
								recommendedSkills = new ArrayList<String>();
								for(String str: recommendedSkillsArr) {
									str = str.replaceAll("\"", "");
									str = str.replace("[", "");
									str = str.replace("]", "");
									recommendedSkills.add(str.trim());
								}
								jobBean.setSkills(recommendedSkills);
								resultList.add(jobBean);
			            	}
			            }
			        }
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;
	}
	
}
