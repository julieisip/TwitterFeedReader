package com.twitter.feed.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twitter.feed.twitter_pojo.TwitterResponse;

@Controller
public class TwitterFeedController {

	/*
	 * This returns a JSON List of twitter feed and sends to response body 
	 * 
	 * */
	@RequestMapping(value = "/reader", method = RequestMethod.GET)
	public @ResponseBody List<TwitterFeed> twitterFeedReader() throws Exception {
		
		//create feedReader object
		TwitterFeedProvider feedReader = new TwitterFeedProvider();
		
		//Retrieve list of twitter feed
		 List<TwitterFeed> feeds = feedReader.getTwitterFeedModel();
		
	
		return feeds; 

	}

}
