package com.twitter.feed.twitter_pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auth {
	private String token_type;
	private String access_token;
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}
	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}
