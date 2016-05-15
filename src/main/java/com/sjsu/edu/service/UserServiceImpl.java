package com.sjsu.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sjsu.edu.beans.DashBoardBean;
import com.sjsu.edu.beans.JobRecommendationBean;
import com.sjsu.edu.beans.LoginForm;
import com.sjsu.edu.beans.SkillsProficiencyBean;
import com.sjsu.edu.beans.UserForm;
import com.sjsu.edu.dao.IUserDAO;
import com.sjsu.edu.dao.UserDAOImpl;

public class UserServiceImpl implements IUserService {

	@Override
	public UserForm login(LoginForm loginForm) {
		IUserDAO obj = new UserDAOImpl();
		return obj.login(loginForm);
	}
	
	@Override
	public int getNextUserSequence(String name) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getNextUserSequence(name);
	}

	@Override
	public boolean createProfile(UserForm userForm) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.createProfile(userForm);
	}
	
	@Override
	public List<String> getRecommendedSkills(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getRecommendedSkills(userID);
	}

	@Override
	public Map<String, Integer> getExistingSkills(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getExistingSkills(userID);
	}

	@Override
	public DashBoardBean getDashBoardData(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getDashBoardData(userID);
	}

	@Override
	public List<JobRecommendationBean> getDefaultJobs(List<SkillsProficiencyBean> skills) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getDefaultJobs(skills);
	}

	@Override
	public List<JobRecommendationBean> getRecommendedJobs(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getRecommendedJobs(userID);
	}

	@Override
	public List<SkillsProficiencyBean> getExistingSkillIdAndProf(int userID) {
		List<SkillsProficiencyBean> result = new ArrayList<SkillsProficiencyBean>();
		SkillsProficiencyBean bean = null;
		IUserDAO userDAO = new UserDAOImpl();
		Map<Integer, Integer> existingSkills = userDAO.getExistingSkillIdAndProf(userID);
		for(Map.Entry<Integer, Integer> entry : existingSkills.entrySet()) {
			bean = new SkillsProficiencyBean();
			bean.setSkillId(entry.getKey());
			bean.setProficiency(entry.getValue());
			result.add(bean);
		}
		return result;
	}

	@Override
	public boolean applyJob(int userID, int jobId) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.applyJob(userID, jobId);
	}

	@Override
	public List<JobRecommendationBean> getAppliedJobs(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getAppliedJobs(userID);
	}

	@Override
	public boolean saveJob(int userID, int jobId) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.saveJob(userID, jobId);
	}

	@Override
	public List<JobRecommendationBean> getSavedJobs(int userID) {
		IUserDAO userDAO = new UserDAOImpl();
		return userDAO.getSavedJobs(userID);
	}

}
