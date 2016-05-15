package com.sjsu.edu.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sjsu.edu.beans.SkillsProficiencyBean;
import com.sjsu.edu.dao.IParserDAO;
import com.sjsu.edu.dao.ParserDAOImpl;

/*
	This class represents the Parser class, it will take in an input text, parse it, analyze it and then output
	the generated meaningful sentences as well as hot keywords.
*/
/**
 * @author Varun
 *
 */
public class CareerParser {

	private List<String> delimiters = null;
	private Map<String, Integer> keywords = null;
	private Map<String, Integer> keywordsFound = null;
	private List<String> lines = null;
	private List<String> verbs = null;
	private List<SkillsProficiencyBean> result = null;

	// directories of various system files
	public final static String DELIMITERS_FILE = "please give your systems path\\delimiters.txt";
	public final static String VERBS_FILE = "please give your systems path\\verbs.txt";

	public CareerParser(){
		keywordsFound = new TreeMap<String, Integer>();
		result = new ArrayList<SkillsProficiencyBean>();
		initDelimiters();
		initVerbs();
	}

	/**
	 * 
	 */
	private void initVerbs(){
		verbs = new ArrayList<String>();
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File(VERBS_FILE)));
			String str = in.readLine();
			while (str != null){
				verbs.add(str);
				str = in.readLine();
			}
		}
		// if the file is not found, the program displays an error message and exits
		catch(IOException e){
			System.out.println("The file " + VERBS_FILE + " was not found.");
			System.exit(0);
		}
	}

	/**
	 * 
	 */
	private void initDelimiters(){
		delimiters = new ArrayList<String>();
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File(DELIMITERS_FILE)));
			String str = in.readLine();
			while (str != null){
				delimiters.add(" " + str + " ");
				str = in.readLine();
			}
			delimiters.add(". ");
			delimiters.add("; ");
			delimiters.add("| ");
			delimiters.add(", ");
			delimiters.add(": ");
		}
		// if the file is not found, the program displays an error message and exits
		catch(IOException e){
			System.out.println("The file " + DELIMITERS_FILE + " was not found.");
			System.exit(0);
		}
	}

	/**
	 * @param str
	 */
	public void getInput(String str){
		lines = new ArrayList<String>(Arrays.asList(str.split("[\\.;|,:]")));
		System.out.println(lines.size());
	}

	/**
	 * @param str
	 * @return
	 */
	private String cleanUp(String str){
		if (str.length() == 0){
			return "";
		}
		String ending = str.substring(str.length() - 1);
		if (ending.matches("^[a-zA-Z0-9_]*$")){
			return str;
		}
		return cleanUp(str.substring(0, str.length() - 1));
	}

	/**
	 * 
	 */
	public void analyze(){
		for (String str : lines){
			for (String word : str.split(" ")) {
				word = cleanUp(word);
				if (keywords.containsKey(word.toLowerCase())) {
					if (!keywordsFound.containsKey(word.toLowerCase())){
						keywordsFound.put(word.toLowerCase(), keywords.get(word.toLowerCase()));
					}
				}
			}
		}
	}

	/**
	 * display()
	 */
	private void display(){
		SkillsProficiencyBean skillsProficiency = null;
		Iterator<String> itr = keywordsFound.keySet().iterator();
		while(itr.hasNext()) {
			String str = itr.next();
			skillsProficiency = new SkillsProficiencyBean();
			skillsProficiency.setSkillId(keywordsFound.get(str));
			skillsProficiency.setSkill(str);
			result.add(skillsProficiency);
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public List<SkillsProficiencyBean> parseResume(String str) {
		IParserDAO parserDAO = new ParserDAOImpl();
		keywords = parserDAO.getSkills();
		getInput(str);
		analyze();
		display();
		return result;
	}
}
