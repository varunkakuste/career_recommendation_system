package com.sjsu.edu.beans;

import java.util.List;

public class JobRecommendationBean {
	
	private int jobId;
	private String companyName;
	private String position;
	private String location; 
	private int percentageMatched; 
	private List<String> skills;
	
	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the percentageMatched
	 */
	public int getPercentageMatched() {
		return percentageMatched;
	}
	/**
	 * @param percentageMatched the percentageMatched to set
	 */
	public void setPercentageMatched(int percentageMatched) {
		this.percentageMatched = percentageMatched;
	}
	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
}
