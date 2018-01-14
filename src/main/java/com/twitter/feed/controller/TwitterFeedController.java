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

	private static final String CONSUMER_KEY = "bAhSf3MFyuZXP8tQxsjOJVozV";
	private static final String CONSUMER_KEY_SECRET = "OmnSQOZ1whcwmj0T55VUIEXYV7hdTxwyvEyutmLddlVcvaMY8A";
	private static final String ACCESS_TOKEN = "1254365198-sYlQwrF8GdGfIhEgDBUyo7HO4OzaF5J7VHOTN26";
	private static final String ACCESS_SECRET = "ylX6iUx4EmLcRX0KvyRGgNC57lkv6MxDaI5bMOqMKK3Td";

	@RequestMapping(value = "/reader", method = RequestMethod.GET)
	public @ResponseBody List<TwitterFeed> twitterFeedReader() throws Exception {
		ModelAndView mav = new ModelAndView("reader");
		TwitterFeedProvider feed = new TwitterFeedProvider();
		feed.getTwitterFeed();
		mav.addObject("Feed", feed.getTwitterFeed());
	
		return feed.getTwitterFeed(); 

	}

}
