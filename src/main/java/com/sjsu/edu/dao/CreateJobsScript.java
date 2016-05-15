package com.sjsu.edu.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class CreateJobsScript {
	
	public static void main(String[] args) {
		createJobs();
	}
	
	public static void createJobs() {
		List<Integer> skills = new ArrayList<Integer>();
		skills.add(1);
		skills.add(2);
		skills.add(3);
		skills.add(4);
		skills.add(5);
		skills.add(6);
		skills.add(7);
		skills.add(8);
		skills.add(9);
		skills.add(10);
		skills.add(11);
		skills.add(12);
		skills.add(13);
		skills.add(14);
		skills.add(15);
		skills.add(16);
		skills.add(17);
		skills.add(18);
		skills.add(19);
		skills.add(20);
		skills.add(21);
		skills.add(22);
		skills.add(23);
		skills.add(24);
		skills.add(25);
		skills.add(26);
		skills.add(27);
		skills.add(28);
		skills.add(29);
		skills.add(30);
		skills.add(31);
		skills.add(32);
		skills.add(33);
		skills.add(34);
		skills.add(35);
		
		List<Integer> skillProficiency = new ArrayList<Integer>();
		skillProficiency.add(1);
		skillProficiency.add(3);
		skillProficiency.add(5);
		
		List<String> city = new ArrayList<String>();
		city.add("San Jose");
		city.add("Fremont");
		city.add("Sacramento");
		city.add("Los Angeles");
		city.add("Alameda");
		city.add("San Mateo");
		city.add("Santa Barbara");
		city.add("Orange");
		city.add("Santa Clara");
		city.add("Fresno");
		city.add("Riverside");
		city.add("San Diego");
		city.add("Irvine");
		city.add("Monterey");
		city.add("Contra Costa");
		city.add("Oak Park");
		city.add("Burbank");
		city.add("Campo");
		city.add("Bloomington");
		city.add("Nipton");
		city.add("San Bernardino");
		city.add("Santa Ana");
		city.add("Simi Valley");
		city.add("Raymond");
		city.add("Mountain View");
		city.add("Redwood City");
		city.add("Woodside");
		city.add("San Bruno");
		city.add("Sunnyvale");
		city.add("Oakland");
		city.add("San Gregorio");
		city.add("San Francisco");
		city.add("Danville");
		city.add("Hayward");
		city.add("Dublin");
		city.add("Pleasanton");
		city.add("Union City");
		city.add("San Ramon");
		city.add("Suisun City");
		city.add("Berkeley");
		city.add("Campbell");
		
		List<String> company = new ArrayList<String>();
		company.add("Adobe Systems");
		company.add("Aldon Inc");
		company.add("AppDynamics");
		company.add("AppFolio");
		company.add("Doximity");
		company.add("Emeter");
		company.add("TripAdvisor");
		company.add("Braintree");
		company.add("Yelp");
		company.add("Dropbox");
		company.add("Houzz");
		company.add("ModCloth");
		company.add("Box");
		company.add("Equinix");
		company.add("GT Nexus");
		company.add("ICharts");
		company.add("Intel");
		company.add("PayPal");
		company.add("eBay");
		company.add("SAP");
		company.add("Shutterfly");
		company.add("Design Science");
		company.add("Intuit");
		company.add("Saba");
		company.add("Twilio");
		company.add("TeraData");
		company.add("Sikka Software");
		company.add("Azumo LLC");
		company.add("Google");
		company.add("VISA");
		company.add("Yahoo");
		company.add("Amazon");
		company.add("Litepoint");
		company.add("SunRun");
		company.add("Solar City");
		company.add("Walmart ECommerce");
		company.add("Zero");
		company.add("Uber Technologies");
		company.add("Lyft");
		company.add("F5 Networks");
		company.add("Microsoft");
		company.add("A10 Networks");
		company.add("Lending Clubs");
		company.add("IBM");
		company.add("Persistent Systems");
		company.add("Verizon");
		company.add("Apple");
		company.add("Dell");
		company.add("Fitbit");
		company.add("Collabera");
		company.add("Palo Alto Networks");
		company.add("eHarmony");
		company.add("Tableau Software Inc");
		company.add("Turo");

		List<String> jobTitle = new ArrayList<String>();
		jobTitle.add("Software Engineer");
		jobTitle.add("Software Engineer I");
		jobTitle.add("Software Engineer II");
		jobTitle.add("Software Engineer III");
		jobTitle.add("Software Engineer New Graduate");
		jobTitle.add("Senior Software Engineer");
		jobTitle.add("Web Engineer");
		jobTitle.add("Front End Engineer");
		jobTitle.add("JavaScript Engineer");
		jobTitle.add("Cloud Engineer");
		jobTitle.add("Data Engineer");
		jobTitle.add("Software Engineer");
		jobTitle.add("iOS Software Engineer");
		jobTitle.add("Swift Developer");
		jobTitle.add("Software Engineer, Game Engine");
		jobTitle.add("Front End Software Engineer");
		jobTitle.add("Software Engineer (optical engineer)");
		jobTitle.add("Software Engineer - Machine Learning");
		jobTitle.add("Web Developer");
		jobTitle.add("Full Stack Software Engineer");
		jobTitle.add("MTS");
		jobTitle.add("Member of Technical Staff");
		
		Set<String> combinationSet = new HashSet<String>();
		Set<Integer> skillsSet = null;
	    int id = 1;
	    String combination = null;
		String cityToInsert = null;
		String companyToInsert = null;
		String jobTitleToInsert = null;
		List<BasicDBObject> jobsToInsert = new ArrayList<BasicDBObject>();
		try {
			MongoDB mongodb = new MongoDB();
			DBCollection dbobj = mongodb.getCollection("Jobs");
			BasicDBObject doc = null;
			BasicDBObject reqSkills = null;
	    	while(combinationSet.size() < 3000) {
				cityToInsert = city.get((int) (Math.floor(Math.random() * city.size())));
				companyToInsert = company.get((int) (Math.floor(Math.random() * company.size())));
				jobTitleToInsert = jobTitle.get((int) (Math.floor(Math.random() * jobTitle.size())));
				combination = cityToInsert + "_" + companyToInsert + "_" + jobTitleToInsert;
				
				if(!combinationSet.contains(combination)) {
					int numOfSkills = (int) (Math.floor(Math.random() * 7) + 1);
					skillsSet = new HashSet<Integer>();
					reqSkills = new BasicDBObject();
					while(skillsSet.size() <= numOfSkills) {
						int randomSkill = (int) (Math.floor(Math.random() * skills.size()));
						int randomSkillProficiency = (int) (Math.floor(Math.random() * skillProficiency.size()));
						
						if(!skillsSet.contains(randomSkill) && randomSkill > 0) {
							reqSkills.append(String.valueOf(skills.get(randomSkill)), skillProficiency.get(randomSkillProficiency));
						}
						skillsSet.add(randomSkill);
					}
					
					doc = new BasicDBObject("JobID", id)
						.append("JobTitle", jobTitleToInsert)
						.append("CompanyName", companyToInsert)
						.append("RequiredSkills", reqSkills)
						.append("City", cityToInsert)
						.append("State", "CA")
						.append("WorkExperience", (int)Math.floor(Math.random() * 5))
						.append("type", "Full-time")
						.append("StartDate", "05/01/2016")
						.append("NewDate", "08/15/2016");
					jobsToInsert.add(doc);
					id++;
				}
				combinationSet.add(combination);
	    	}
	    	dbobj.insert(jobsToInsert);
		} catch(Exception expt) {
			System.out.println(expt);
		}
	}
}
