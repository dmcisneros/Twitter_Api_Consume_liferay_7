package com.everis.codefest.example.Controller;

import com.everis.codefest.example.Constants.Constants;
import com.everis.codefest.example.Utils.Utils;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=#Codefest_2016",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CodeFestExamplePortlet extends MVCPortlet {
	
	/**
	 * OAuthConsumerKey
	 */
	private String OAuthConsumerKey= PropsUtil.get(Constants.OAUTH_CONSUMER_KEY);
	
	/**
	 * ConsumerSecret configurado
	 */
	private String OAuthConsumerSecret= PropsUtil.get(Constants.OAUTH_CONSUMER_SECRET);
	
	/**
	 * TokenSecret configurado
	 */
	private String OAuthAccessTokenSecret= PropsUtil.get(Constants.OAUTH_ACCESS_TOKEN_SECRET);
	
	/**
	 * Token de acceso configurado
	 */
	private String OAuthAccessToken= PropsUtil.get(Constants.OAUTH_ACCESS_TOKEN);
	
	/**
	 * Hasthag configurado
	 */
	private String hashtag = PropsUtil.get(Constants.TWITTER_HASHTAG);
	
	/**
	 * Clase propia de Twitter
	 */
	private static Twitter twitter;
	
	/**
	 * Resultados de la búsqueda
	 */
	QueryResult results = null;
	
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		
		// Realizamos la búsqueda del hashtag configurado
		getTwitterHashtag();		
		
		renderRequest.setAttribute(Constants.RESULTS, results);
		super.doView(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		List<Long> oldTweets = new ArrayList<Long>();
		List<Status> newTweets = new ArrayList<Status>();	
		
		results = Utils.getOldTwitts(results, oldTweets);
				
		// Realizamos la búsqueda del hashtag configurado
		getTwitterHashtag();
		
		newTweets = Utils.getNewTwitts(results, oldTweets, newTweets);
		
		resourceRequest.setAttribute(Constants.TWEETS_RESULTS, newTweets);
		include("/tweet.jsp", resourceRequest, resourceResponse);
	}
		
	private void getTwitterHashtag() {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(OAuthConsumerKey);
		cb.setOAuthConsumerSecret(OAuthConsumerSecret);
		cb.setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
		cb.setOAuthAccessToken(OAuthAccessToken);
				
		//PROXY CONFIGURATION
 		//cb.setHttpProxyHost("10.125.8.100");
		//cb.setHttpProxyPort(8080);
			
		try {
			
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();	
			
			Query query = new Query(hashtag);	        
			results = twitter.search(query);

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	
	}
}