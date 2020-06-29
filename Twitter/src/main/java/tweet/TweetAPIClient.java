package tweet;
import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class TweetAPIClient  extends TwitterAPIClient {
    private final String GET_PLACES_NEAR_ENDPOINT = "/geo/reverse_geocode.json";
    public ValidatableResponse GETPLACESNEARENDPOINT(String atUsername){  
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri+this.GET_PLACES_NEAR_ENDPOINT)
                .then();
            }  
    private final String GET_USER_TWEETS_ENDPOINT="statuses/user_timeline.json";
    public ValidatableResponse getUserTimelineTweets(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri+this.GET_USER_TWEETS_ENDPOINT)
                .then();
            }
    public ValidatableResponse puteTweet( Long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUri+this.GET_USER_TWEETS_ENDPOINT)
                .then();
            }

// Create a tweet from user's twitter
    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",tweet)
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
        
    private final String ADD_FAVORITES_ENDPOINT = "/search/tweets.json";
    public ValidatableResponse ADDFAVORITESENDPOINT(String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri+this.ADD_FAVORITES_ENDPOINT)
                .then();
            }
    private final String GET_USER_SEARCH="/users/search.json";
    public ValidatableResponse getUserSearch(String search){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("search",search)
                .when().get(this.baseUri + this.GET_USER_SEARCH)
                .then();
            }
    private final String SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";
    public ValidatableResponse searchTweets(String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri + this.SEARCH_TWEETS_ENDPOINT)
                .then();
            }
    // Create a List of tweets from user's twitter with EXCEL SHEET
    public ValidatableResponse createListTweets(String Flights ){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",Flights )
                .when().post(this.baseUri+this.CREATE_TWEET_ENDPOINT)
                .then();
            }
}
