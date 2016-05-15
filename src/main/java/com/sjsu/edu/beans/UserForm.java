package com.sjsu.edu.beans;

import java.util.List;

public class UserForm {
	private Integer userId;
	private List<SkillsProficiencyBean> skillsProficiency;
	private String address;
	private String city;
	private String state;
	private String country;
	private String gender;
	private String location;
	private SignupForm signupForm;
	private DashBoardBean dashBoardBean;
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the skillsProficiency
	 */
	public List<SkillsProficiencyBean> getSkillsProficiency() {
		return skillsProficiency;
	}
	/**
	 * @param skillsProficiency the skillsProficiency to set
	 */
	public void setSkillsProficiency(List<SkillsProficiencyBean> skillsProficiency) {
		this.skillsProficiency = skillsProficiency;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the signupForm
	 */
	public SignupForm getSignupForm() {
		return signupForm;
	}
	/**
	 * @param signupForm the signupForm to set
	 */
	public void setSignupForm(SignupForm signupForm) {
		this.signupForm = signupForm;
	}
	/**
	 * @return the dashBoardBean
	 */
	public DashBoardBean getDashBoardBean() {
		return dashBoardBean;
	}
	/**
	 * @param dashBoardBean the dashBoardBean to set
	 */
	public void setDashBoardBean(DashBoardBean dashBoardBean) {
		this.dashBoardBean = dashBoardBean;
	}
	
}
