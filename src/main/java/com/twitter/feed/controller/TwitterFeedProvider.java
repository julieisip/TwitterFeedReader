package com.twitter.feed.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.feed.twitter_pojo.Auth;
import com.twitter.feed.twitter_pojo.Media;
import com.twitter.feed.twitter_pojo.TwitterResponse;

public class TwitterFeedProvider {

	private String ConsumerKey;
	private String ConsumerSecret;

	public List<TwitterFeed> getTwitterFeed() throws Exception {

		String twitterFeed = null;
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

		ObjectMapper mapper = new ObjectMapper();

		List<TwitterResponse> feeds = mapper.readValue(twitterFeed, new TypeReference<List<TwitterResponse>>() {
		});

		List<TwitterFeed> twitterFeeds = new ArrayList<TwitterFeed>();

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
			
			twitterFeeds.add(twitFeed);

		}

		return twitterFeeds;
	}

	private String getAuthentication() throws Exception {

		String auth = null;

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

		ObjectMapper mapper = new ObjectMapper();

		Auth token = mapper.readValue(auth, Auth.class);

		return token.getAccess_token();

	}

	public String getConsumerKey() {
		return ConsumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		ConsumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return ConsumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		ConsumerSecret = consumerSecret;
	}

}
