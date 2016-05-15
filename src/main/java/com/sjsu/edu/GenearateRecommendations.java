package job.recommendations;


public class GenearateRecommendations {
	public static void main(String[] args) {
		SkillRecommendations skills = null;
		JobRecommendationBasedOnUserProfile generateRecommendations = null;
		getRecommendedJobsBean getRecommendedJobBean = null;
		try {
			//Skill Recommendations
			skills = new SkillRecommendations();
			skills.generateSkillsRecs();
			
			//Job Recommendations
			generateRecommendations = new JobRecommendationBasedOnUserProfile();
			generateRecommendations.addUserProfile();
			getRecommendedJobBean = new getRecommendedJobsBean();
			getRecommendedJobBean.getRecommendedJobData();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
