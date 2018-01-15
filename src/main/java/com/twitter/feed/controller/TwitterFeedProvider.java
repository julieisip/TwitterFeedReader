package com.twitter.feed.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.feed.twitter_pojo.Auth;
import com.twitter.feed.twitter_pojo.Media;
import com.twitter.feed.twitter_pojo.TwitterResponse;

public class TwitterFeedProvider {

	public List<TwitterFeed> getTwitterFeedModel() throws Exception {

		List<TwitterFeed> twitterFeeds = new ArrayList<TwitterFeed>();

		// call to method to gett the twitter feed
		List<TwitterResponse> feeds = getTwitterResponse();

		// map the twitterResponse object to TwitterFeed Model
		for (TwitterResponse feed : feeds) {

			TwitterFeed twitFeed = new TwitterFeed();

			twitFeed.setUsername(feed.getUser().getName());
			twitFeed.setContent(feed.getText());
			twitFeed.setRetweetCount(feed.getRetweet_count());
			twitFeed.setUserProfileImage(feed.getUser().getProfile_image_url());
			twitFeed.setUserScreenName(feed.getUser().getScreen_name());
			List<Media> media = feed.getEntities().getMedia();
			if (media != null) {

				twitFeed.setMedia(media.get(0).getMedia_url());
			}
			twitFeed.setDate(feed.getCreated_at());

			twitterFeeds.add(twitFeed);

		}

		return twitterFeeds;

	}

	/*
	 * Twitter API client calls twitter api and maps to TwitterResponse Object
	 * 
	 */
	private List<TwitterResponse> getTwitterResponse() throws Exception {

		String twitterFeed = null;

		// Get Request to twitter api to get 10 latest feed from salesforce
		// timeline
		try {

			UriComponentsBuilder url = UriComponentsBuilder
					.fromHttpUrl("https://api.twitter.com/1.1/statuses/user_timeline.json")
					.queryParam("screen_name", "salesforce").queryParam("count", "10");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.set("Authorization", "Bearer " + getAuthentication());

			headers.set("Accept", MediaType.ALL_VALUE);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<String> response = restTemplate.exchange(url.build().encode().toUri(), HttpMethod.GET,
					entity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				twitterFeed = response.getBody();

			} else {
				throw new Exception("twitter does not respond");

			}
		} catch (Exception e) {

			throw new Exception("twitter unreachable");
		}
		System.out.println("call twitter api");
		// Maps response body to List of TwitterResponse
		ObjectMapper mapper = new ObjectMapper();

		List<TwitterResponse> feeds = mapper.readValue(twitterFeed, new TypeReference<List<TwitterResponse>>() {
		});

		return feeds;
	}

	/*
	 * Generate Bearer Token Call twitter api to generate Bearer
	 */
	private String getAuthentication() throws Exception {

		String auth = null;

		// Post Request to twitter api generate oauth token
		try {
			UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl("https://api.twitter.com/oauth2/token");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.set("Authorization", "Basic "
					+ "YkFoU2YzTUZ5dVpYUDh0UXhzak9KVm96VjpPbW5TUU9aMXdoY3dtajBUNTVWVUlFWFlWN2hkVHh3eXZFeXV0bUxkZGxWY3ZhTVk4QQ==");

			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			HttpEntity<String> entity = new HttpEntity<String>("grant_type=client_credentials", headers);

			ResponseEntity<String> response = restTemplate.exchange(url.build().encode().toUri(), HttpMethod.POST,
					entity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				auth = response.getBody();

			} else {
				throw new Exception(" twitter authorization does not respond");

			}
		} catch (Exception e) {

			throw new Exception("twitter unreachable");
		}

		// Maps response body to Auth Object
		ObjectMapper mapper = new ObjectMapper();

		Auth token = mapper.readValue(auth, Auth.class);

		return token.getAccess_token();

	}

}
