package com.sjsu.edu.beans;

import java.util.List;
import java.util.Map;

public class SkillRecommendationBean {
	private List<String> recommendedSkillsList; 
	private Map<String,Integer> existingSkillsMap;
	
	/**
	 * @return the recommendedSkillsList
	 */
	public List<String> getRecommendedSkillsList() {
		return recommendedSkillsList;
	}
	/**
	 * @param recommendedSkillsList the recommendedSkillsList to set
	 */
	public void setRecommendedSkillsList(List<String> recommendedSkillsList) {
		this.recommendedSkillsList = recommendedSkillsList;
	}
	/**
	 * @return the existingSkillsMap
	 */
	public Map<String, Integer> getExistingSkillsMap() {
		return existingSkillsMap;
	}
	/**
	 * @param existingSkillsMap the existingSkillsMap to set
	 */
	public void setExistingSkillsMap(Map<String, Integer> existingSkillsMap) {
		this.existingSkillsMap = existingSkillsMap;
	}
}
