package com.twitter.feed.twitter_pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {
	private List<Url> urls;
	private List<Media> media;
	private String screen_name;
	private String profile_image_url;
	/**
	 * @return the urls
	 */
	public List<Url> getUrls() {
		return urls;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(List<Url> urls) {
		this.urls = urls;
	}
	/**
	 * @return the media
	 */
	public List<Media> getMedia() {
		return media;
	}
	/**
	 * @param media the media to set
	 */
	public void setMedia(List<Media> media) {
		this.media = media;
	}
	/**
	 * @return the screen_name
	 */
	public String getScreen_name() {
		return screen_name;
	}
	/**
	 * @param screen_name the screen_name to set
	 */
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	/**
	 * @return the profile_image_url
	 */
	public String getProfile_image_url() {
		return profile_image_url;
	}
	/**
	 * @param profile_image_url the profile_image_url to set
	 */
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	

}
