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
	
//	public DBCollection getCollection(String collectionName) throws UnknownHostException, MongoException {
////		String textUri = "mongodb://CRS:twinklestars123@@ds023442.mlab.com:23442/career";
////		MongoClientURI uri = new MongoClientURI(textUri);
////		MongoClient mongoClient = new MongoClient(uri);
//		
//		
//		
//		MongoCredential credential = MongoCredential.createCredential("varun", "career", "varun".toCharArray());  //("CRS", "career", "twinklestars123".toCharArray());
//		String mongoHost = "ds023442.mlab.com";
//		int mongoPort = 23442;
//		
//		ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);
//		MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
//		
//		
//		
////		ServerAddress addr = new ServerAddress("ds019970.mlab.com", 23442);
////
////	    MongoCredential credential = MongoCredential.createCredential("CRS", "career", "twinklestars123".toCharArray());
////	    MongoClientOptions options = MongoClientOptions.builder().serverSelectionTimeout(1000).build();
////	    MongoClient mongoClient = new MongoClient(addr, Arrays.asList(credential), options);
//		
////		MongoCredential mongoCredential = MongoCredential.createMongoCRCredential("CRS", "career", "twinklestars123".toCharArray());
//		DB db = mongoClient.getDB(DATABASE_NAME);
////		db.authenticate("varunkakuste", "varun@124".toCharArray());
//		DBCollection dbobj = db.getCollection(collectionName);
//		return dbobj;
//		
////		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
////		DBCollection dbobj = (DBCollection) db.getCollection(collectionName); //.getCollection(collectionName);
////		return dbobj;
//	}
	
}
