package com.sjsu.edu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.sjsu.edu.beans.DashBoardBean;
import com.sjsu.edu.beans.JobRecommendationBean;
import com.sjsu.edu.beans.LoginForm;
import com.sjsu.edu.beans.SignupForm;
import com.sjsu.edu.beans.SkillRecommendationBean;
import com.sjsu.edu.beans.SkillsProficiencyBean;
import com.sjsu.edu.beans.UserForm;
import com.sjsu.edu.parser.CareerParser;
import com.sjsu.edu.service.IUserService;
import com.sjsu.edu.service.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Locale locale, Model model) {
		logger.info("HomeController: homePage()");
		return "landing";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		logger.info("HomeController: homePage()");
		return "landing";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model, @ModelAttribute("loginForm") LoginForm loginFrom, HttpServletRequest request) {
		ModelAndView result = null;
		IUserService obj = new UserServiceImpl();
		UserForm userDetails = obj.login(loginFrom);
		if(userDetails == null) {
			result = new ModelAndView("error", "errorMessage", "<h1>User not found</h1><br><br><a href='register'>Click here </a>to register...");
		} else {
			result = new ModelAndView("home", "userForm", userDetails);
			HttpSession session = request.getSession();
			session.setAttribute("userDetails", userDetails);
			session.setAttribute("userName", userDetails.getSignupForm().getFirst_name() + " " +userDetails.getSignupForm().getLast_name());
		}
		return result;
	}
	
	/**
	 * logoutPage()
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request) {
		logger.info("HomeController class --->>> logoutPage() Method Start");
		HttpSession session = request.getSession();
		session.invalidate();
		logger.info("HomeController class --->>> logoutPage() Method End");
        return "redirect:/";
    }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		DashBoardBean dashBoardBean = dashBoard(userDetails.getUserId());
		dashBoardBean.setFirstTime(false);
		userDetails.setDashBoardBean(dashBoardBean);
		ModelAndView result = new ModelAndView("home", "userForm", userDetails);
		return result;
	}
	
	/**
	 */
	public DashBoardBean dashBoard(int userId) {
		IUserService obj = new UserServiceImpl();
		DashBoardBean bean = obj.getDashBoardData(userId);
		return bean;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(Locale locale, Model model, @ModelAttribute("signupForm") SignupForm signupForm, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		List<SkillsProficiencyBean> skillProfList = null;
		UserForm userForm = null;
		IUserService userService = null;
		ModelAndView result = null;
		try {
			skillProfList = uploadFileHandler(file);
			userForm = new UserForm();
			userForm.setSkillsProficiency(skillProfList);
			userForm.setSignupForm(signupForm);
			userService = new UserServiceImpl();
			Integer userId = userService.getNextUserSequence("userId");
			userForm.setUserId(userId);
			result = new ModelAndView("createprofile", "userForm", userForm);
			HttpSession session = request.getSession();
			session.setAttribute("userName", signupForm.getFirst_name() + " " + signupForm.getLast_name());
		} catch(Exception expt) {
			result = new ModelAndView("landing");
		}
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/createprofile", method = RequestMethod.POST)
	public ModelAndView createProfile(Locale locale, Model model, @ModelAttribute("userForm") UserForm userForm, HttpServletRequest request) {
		IUserService userService = null;
		boolean isCreated = false;
		ModelAndView result = null;
		try{
			userService = new UserServiceImpl();
			isCreated = userService.createProfile(userForm);
			if(isCreated) {
				DashBoardBean bean = new DashBoardBean();
				bean.setFirstTime(true);
				List<JobRecommendationBean> recommendedJobsList = userService.getDefaultJobs(userForm.getSkillsProficiency());
				if(recommendedJobsList != null && !recommendedJobsList.isEmpty()) {
					bean.setNoOfRecommendedJobs(recommendedJobsList.size());
				}
				userForm.setDashBoardBean(bean);
				result = new ModelAndView("home", "userForm", userForm);
				HttpSession session = request.getSession();
				session.setAttribute("userDetails", userForm);
				session.setAttribute("userName", userForm.getSignupForm().getFirst_name() + " " +userForm.getSignupForm().getLast_name());
			} else {
				result = new ModelAndView("error");
			}
		} catch(Exception expt) {
			result = new ModelAndView("createprofile", "userForm", userForm);
		}
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/skillrecommendation", method = RequestMethod.GET)
	public ModelAndView skillRecommendation(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		IUserService userService = new UserServiceImpl();
		List<String> recommendedSkillsList = userService.getRecommendedSkills(userDetails.getUserId());
		Map<String, Integer> existingSkills = userService.getExistingSkills(userDetails.getUserId());
		SkillRecommendationBean recommendedSkillsBean = null;
		
		if(recommendedSkillsList == null || recommendedSkillsList.isEmpty()) {
			recommendedSkillsList = new ArrayList<String>();
		} 
		
		if(existingSkills == null || existingSkills.isEmpty()) {
			existingSkills = new HashMap<String, Integer>();
		} 
		
		recommendedSkillsBean = new SkillRecommendationBean();
		recommendedSkillsBean.setRecommendedSkillsList(recommendedSkillsList);
		recommendedSkillsBean.setExistingSkillsMap(existingSkills);
		result = new ModelAndView("skillrecommendation" , "skillRecommendation", recommendedSkillsBean);
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/jobrecommendation", method = RequestMethod.GET)
	public ModelAndView jobRecommendation(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		List<JobRecommendationBean> recommendedJobsList = null;
		IUserService userService = new UserServiceImpl();
		
		recommendedJobsList = userService.getRecommendedJobs(userDetails.getUserId());
		if(recommendedJobsList == null || recommendedJobsList.isEmpty()) {
			recommendedJobsList = userService.getDefaultJobs(userService.getExistingSkillIdAndProf(userDetails.getUserId()));
			userDetails.getDashBoardBean().setFirstTime(false);
		} 
		
		if(recommendedJobsList != null && !recommendedJobsList.isEmpty()) {
			Collections.sort(recommendedJobsList, new Comparator<JobRecommendationBean>() {
			    @Override
			    public int compare(JobRecommendationBean a, JobRecommendationBean b) {
		    		if (a.getPercentageMatched() == b.getPercentageMatched())
		    			return 0;
		    		else if (b.getPercentageMatched() > a.getPercentageMatched())
		    			return 1;
		    		else
		    			return -1;
			    }
			});
		}
		
		session.setAttribute("userDetails", userDetails);
		result = new ModelAndView("jobrecommendation" , "jobList", recommendedJobsList);
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public ModelAndView jobApply(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		List<JobRecommendationBean> recommendedJobsList = null;
		IUserService userService = new UserServiceImpl();
		
		int appliedJobId = Integer.valueOf(request.getParameter("selectedJob"));		
		boolean isApplied = userService.applyJob(userDetails.getUserId(), appliedJobId);
		if(isApplied) {
			System.out.println("Applied");
		}
		
		recommendedJobsList = userService.getRecommendedJobs(userDetails.getUserId());
		if(recommendedJobsList == null || recommendedJobsList.isEmpty()) {
			recommendedJobsList = userService.getDefaultJobs(userService.getExistingSkillIdAndProf(userDetails.getUserId()));
			userDetails.getDashBoardBean().setFirstTime(false);
		} 
		
		if(recommendedJobsList != null && !recommendedJobsList.isEmpty()) {
			Collections.sort(recommendedJobsList, new Comparator<JobRecommendationBean>() {
			    @Override
			    public int compare(JobRecommendationBean a, JobRecommendationBean b) {
		    		if (a.getPercentageMatched() == b.getPercentageMatched())
		    			return 0;
		    		else if (b.getPercentageMatched() > a.getPercentageMatched())
		    			return 1;
		    		else
		    			return -1;
			    }
			});
		}
		
		session.setAttribute("userDetails", userDetails);
		result = new ModelAndView("jobrecommendation" , "jobList", recommendedJobsList);
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/appliedjobs", method = RequestMethod.GET)
	public ModelAndView appliedJobs(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		List<JobRecommendationBean> recommendedJobsList = null;
		IUserService userService = new UserServiceImpl();
		recommendedJobsList = userService.getAppliedJobs(userDetails.getUserId());
		session.setAttribute("userDetails", userDetails);
		result = new ModelAndView("appliedJobs" , "jobList", recommendedJobsList);
		return result;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView jobSave(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		List<JobRecommendationBean> recommendedJobsList = null;
		IUserService userService = new UserServiceImpl();
		
		int appliedJobId = Integer.valueOf(request.getParameter("selectedJob"));		
		boolean isSaved = userService.saveJob(userDetails.getUserId(), appliedJobId);
		if(isSaved) {
			System.out.println("Saved");
		}
		
		recommendedJobsList = userService.getRecommendedJobs(userDetails.getUserId());
		if(recommendedJobsList == null || recommendedJobsList.isEmpty()) {
			recommendedJobsList = userService.getDefaultJobs(userService.getExistingSkillIdAndProf(userDetails.getUserId()));
			userDetails.getDashBoardBean().setFirstTime(false);
		} 
		
		if(recommendedJobsList != null && !recommendedJobsList.isEmpty()) {
			Collections.sort(recommendedJobsList, new Comparator<JobRecommendationBean>() {
			    @Override
			    public int compare(JobRecommendationBean a, JobRecommendationBean b) {
		    		if (a.getPercentageMatched() == b.getPercentageMatched())
		    			return 0;
		    		else if (b.getPercentageMatched() > a.getPercentageMatched())
		    			return 1;
		    		else
		    			return -1;
			    }
			});
		}
		
		session.setAttribute("userDetails", userDetails);
		result = new ModelAndView("jobrecommendation" , "jobList", recommendedJobsList);
		return result;
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/savedjobs", method = RequestMethod.GET)
	public ModelAndView savedJobs(HttpServletRequest request) {
		ModelAndView result = null;
		HttpSession session = request.getSession();
		UserForm userDetails = (UserForm) session.getAttribute("userDetails");
		List<JobRecommendationBean> recommendedJobsList = null;
		IUserService userService = new UserServiceImpl();
		recommendedJobsList = userService.getSavedJobs(userDetails.getUserId());
		if(recommendedJobsList != null && !recommendedJobsList.isEmpty()) {
			Collections.sort(recommendedJobsList, new Comparator<JobRecommendationBean>() {
			    @Override
			    public int compare(JobRecommendationBean a, JobRecommendationBean b) {
		    		if (a.getPercentageMatched() == b.getPercentageMatched())
		    			return 0;
		    		else if (b.getPercentageMatched() > a.getPercentageMatched())
		    			return 1;
		    		else
		    			return -1;
			    }
			});
		}
		session.setAttribute("userDetails", userDetails);
		result = new ModelAndView("savedJobs" , "jobList", recommendedJobsList);
		return result;
	}
	
	/**
     * Upload single file using Spring Controller
	 * @throws TikaException 
	 * @throws SAXException 
	 * @throws IOException 
     */
    private List<SkillsProficiencyBean> uploadFileHandler(MultipartFile file) {
    	List<SkillsProficiencyBean> skillProfList = null;
    	CareerParser career = null;
    	BodyContentHandler handler = null;
    	Metadata metadata = null;
    	ParseContext pcontext = null;
    	PDFParser pdfparser = null;
    	OOXMLParser  msofficeparser = null;
    	TXTParser textParser = null;
    	String fileName = null;
    	try {
	    	if (!file.isEmpty()) {
	    		career = new CareerParser();
	        	handler = new BodyContentHandler();
	        	metadata = new Metadata();
	        	pcontext = new ParseContext();
	    		fileName = file.getOriginalFilename();
	        	if(fileName.toLowerCase().endsWith(".pdf")) {
	        		pdfparser = new PDFParser(); 
	                pdfparser.parse(file.getInputStream(), handler, metadata, pcontext);
	        	} else if(fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx")) {
	        		msofficeparser = new OOXMLParser(); 
	                msofficeparser.parse(file.getInputStream(), handler, metadata, pcontext);
	        	} else if(fileName.toLowerCase().endsWith(".txt")) {
	        		textParser = new TXTParser();
	        		textParser.parse(file.getInputStream(), handler, metadata, pcontext);
	        	}
	            skillProfList = career.parseResume(handler.toString());
	        }
    	}catch(Exception expt) {
    		System.out.println("Exception while resume parsing");
    	}
    	return skillProfList;
    }
	
}
