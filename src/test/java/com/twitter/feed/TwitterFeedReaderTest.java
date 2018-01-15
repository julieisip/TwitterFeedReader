package com.twitter.feed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.twitter.feed.controller.TwitterFeed;
import com.twitter.feed.controller.TwitterFeedProvider;
import com.twitter.feed.twitter_pojo.Auth;
import com.twitter.feed.twitter_pojo.TwitterResponse;

public class TwitterFeedReaderTest {

	/*
	 * Test getTwitterFeed response map to TwitterFeed Object
	 */
	@Test
	public void getTwitterFeedTest() throws Exception {

		TwitterFeedProvider feedTest = new TwitterFeedProvider();
		List<TwitterFeed> feedList = feedTest.getTwitterFeedModel();
		// test must List contain 10 object
		Assert.assertEquals(10, feedList.size());
		// test the user must be salesForce

		for (TwitterFeed feed : feedList) {
			Assert.assertEquals("Salesforce", feed.getUsername());
			Assert.assertEquals("salesforce", feed.getUserScreenName());

		}
	}

	/*
	 * Test call to twitter api to get salesforce timeline Get Operation
	 */
	@Test
	public void getTwitterResponseTest() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		TwitterFeedProvider feedTest = new TwitterFeedProvider();
		Method privateStringMethod = TwitterFeedProvider.class.getDeclaredMethod("getTwitterResponse");

		privateStringMethod.setAccessible(true);

		@SuppressWarnings("unchecked")
		List<TwitterResponse> returnValue = (List<TwitterResponse>) privateStringMethod.invoke(feedTest);
		// test must List contain 10 object
		Assert.assertEquals(10, returnValue.size());

	}

	/*
	 * Test call to twitter api to get bearer token Post operation
	 */
	@Test
	public void getAuthenticationTest() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		TwitterFeedProvider feedTest = new TwitterFeedProvider();
		Method privateStringMethod = TwitterFeedProvider.class.getDeclaredMethod("getAuthentication");

		privateStringMethod.setAccessible(true);

		String returnValue = (String) privateStringMethod.invoke(feedTest);
		// test auth must not be null
		Assert.assertNotNull(returnValue);

	}
}
