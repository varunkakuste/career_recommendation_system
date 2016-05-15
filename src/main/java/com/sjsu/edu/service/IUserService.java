package com.sjsu.edu.service;

import java.util.List;
import java.util.Map;

import com.sjsu.edu.beans.DashBoardBean;
import com.sjsu.edu.beans.JobRecommendationBean;
import com.sjsu.edu.beans.LoginForm;
import com.sjsu.edu.beans.SkillsProficiencyBean;
import com.sjsu.edu.beans.UserForm;

public interface IUserService {
	public UserForm login(LoginForm loginForm);
	public boolean createProfile(UserForm userForm);
	public int getNextUserSequence(String name);
	public List<String> getRecommendedSkills(int userID);
	public Map<String, Integer> getExistingSkills(int userID);
	public DashBoardBean getDashBoardData(int userID);
	public List<JobRecommendationBean> getDefaultJobs(List<SkillsProficiencyBean> skills);
	public List<JobRecommendationBean> getRecommendedJobs(int userID);
	public List<SkillsProficiencyBean> getExistingSkillIdAndProf(int userID);
	public boolean applyJob(int userID, int jobId);
	public List<JobRecommendationBean> getAppliedJobs(int userID);
	public boolean saveJob(int userID, int jobId);
	public List<JobRecommendationBean> getSavedJobs(int userID);
}
