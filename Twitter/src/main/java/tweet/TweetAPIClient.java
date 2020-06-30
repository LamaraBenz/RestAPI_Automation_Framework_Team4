package tweet;

import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class TweetAPIClient  extends TwitterAPIClient {
    // https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline
    // Tweet Client class that consists of all the different API's of Twitter's Tweet
    // POST: Retrieve and engage with tweets....


    private final String GET_USER_TWEETS_ENDPOINT="statuses/user_timeline.json";
    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    public ValidatableResponse getUserTimelineTweets(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri+this.GET_USER_TWEETS_ENDPOINT)
                .then();
    }

// Create a tweet from user's twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUri+this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    // Create a List of tweets from user's twitter
    public ValidatableResponse createListTweets(String Flights ){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",Flights )
                .when().post(this.baseUri+this.CREATE_TWEET_ENDPOINT)
                .then();
    }


    // Delete a tweet from the user's twitter
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    public ValidatableResponse deleteTweet( Long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUri+this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    private final String GET_USER_MENTIONS_TIMELINE="/mentions_timeline.json";
    public ValidatableResponse getUsermentionstimeline(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri+this.GET_USER_MENTIONS_TIMELINE)
                .then();
    }
    private final String GET_USER_SEARCH="/users/search.json";
    public ValidatableResponse getUserSearch(String search){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("search",search)
                .when().post(this.baseUri+this.GET_USER_SEARCH)
                .then();
    }

    private final String SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";
    public ValidatableResponse searchTweets(String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri+this.SEARCH_TWEETS_ENDPOINT)
                .then();
    }



}
