# TwitterFeedReader



This is simple Twitter feed reader web application that shows the last 10 tweets from
@salesforce ( https://twitter.com/salesforce ) user timeline. 

Each minute this list
of tweets should automatically update to show only the 10 most recent tweets.

There is also an  input field where the user can type to filter the current
list of tweets by whether the input string is present anywhere in the content of a
tweet.

The following details should be displayed in a pleasing format for each tweet:

● User name
● User screen name (@screen_name)
● User profile image
● Tweet content (including images)
● How many times the message was retweeted
● Tweet date 


This Application is build using JAVA SPRING MVC framework

In this application I have created a restful api that calls twitter api for searching timeline of user and calling twitter api to generate
authentication bearer token.

This produces a  JSON  response 

i.e.

https://twitter-feed-reader.herokuapp.com/reader


[  
   {  
      "username":"Salesforce",
      "userScreenName":"salesforce",
      "userProfileImage":"http://pbs.twimg.com/profile_images/716283264881700865/voT_NePC_normal.jpg",
      "content":"RT @Benioff: This weekend as I think of Martin Luther King Jr. I am reminded that only through Love and Forgiveness can we end the division…",
      "retweetCount":307,
      "media":null,
      "date":"Mon Jan 15 19:07:26 +0000 2018"
   },
   ]


The Application UI is implement using backbone.js for data binding, underscore.js for templating, and twitter Bootstrap



this consumes the response from the Rest API and displays the response in the UI with formatting and styling.




