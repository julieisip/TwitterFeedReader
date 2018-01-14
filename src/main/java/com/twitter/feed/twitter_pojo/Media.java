package com.twitter.feed.twitter_pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {
	private String media_url;

	/**
	 * @return the media_url
	 */
	public String getMedia_url() {
		return media_url;
	}

	/**
	 * @param media_url the media_url to set
	 */
	public void setMedia_url(String media_url) {
		this.media_url = media_url;
	}

}
