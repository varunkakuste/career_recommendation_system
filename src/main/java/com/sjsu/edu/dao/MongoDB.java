package com.sjsu.edu.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoDB {

	private static final String URI = "localhost";
	private static final Integer PORT = 27017;
	private static final String DATABASE_NAME = "career";

	/**
	 * Create MongoDB collection with Mongolab
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public DBCollection getCollection(String collectionName) throws UnknownHostException, MongoException {
		MongoClient mongoClient = new MongoClient(URI, PORT);
		DB db = mongoClient.getDB(DATABASE_NAME);
		DBCollection dbobj = db.getCollection(collectionName);
		return dbobj;
	}
}
