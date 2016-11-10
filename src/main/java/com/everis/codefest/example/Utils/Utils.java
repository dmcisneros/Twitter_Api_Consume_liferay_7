package com.everis.codefest.example.Utils;

import java.util.List;

import twitter4j.QueryResult;
import twitter4j.Status;

public class Utils {
	
	public static QueryResult getOldTwitts(QueryResult results, List<Long> oldTweets) {
		
		QueryResult resultado = null ;		
		
		for(Status record : results.getTweets()){
			oldTweets.add(record.getId());
		}		
		
		return resultado;
	}
	
	public static List<Status> getNewTwitts(QueryResult results, List<Long> oldTweets, List<Status> newTweets) {
		
		boolean found = false;
		
		for(int index = 0; index < results.getTweets().size() && !found; index++){
			
			if (!oldTweets.isEmpty() && oldTweets.get(0).longValue()==(results.getTweets().get(index).getId())){
				found = true;
			} else{
				newTweets.add(results.getTweets().get(index));
			}
		}
		return newTweets;
	}

}
