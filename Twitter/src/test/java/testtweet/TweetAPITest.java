package testtweet;
import base.DataProviders;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;
import java.util.UUID;
public class TweetAPITest {
    private TweetAPIClient tweetAPIClient;
    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }
    @Test
//    @org.testng.annotations.AfterTest
    public void testUserCanTweetSuccessfully(){
        // 1. User send a tweet
        String tweet="LAMARA"+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was successfully send
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    // Write an API test where user can not twee the same tweet twice in a row
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. User send a tweet
        String tweet="Tweet "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successfully send
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // 3. User sends the same tweet again
        response=this.tweetAPIClient.createTweet(tweet);
        // 4. Verify that the tweet was unsuccessfull
        response.statusCode(403);
        String expectedMessage="Status is a duplicate.";
        String actualMessage=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage,expectedMessage);
    }
    @Test
    public void testDeleteTweet(){
     String tweet="Tweet "+ UUID.randomUUID().toString();
                 //ValidatableResponse response=this.tweetAPIClient.createTweet(tweet).assertThat()
//                .body("id",equals());
       ValidatableResponse response=this.tweetAPIClient.deleteTweet(1277129311649177600L);
        //ValidatableResponse response1=this.tweetAPIClient.deleteTweet(1277126021230231553L);
    }

    @Test
    public void TestgetUserSearch(){
       String search="Hakim Bouaraba";
        tweetAPIClient.getUserSearch("Hakim Bouaraba");
        ValidatableResponse response=this.tweetAPIClient.getUserSearch("Hakim Bouaraba");
        //response.statusCode(200);
        String expectedUser=response.extract().body().path("text");
        Assert.assertEquals( search,expectedUser);
//        ValidatableResponse user = tweetAPIClient.getUserSearch("Hakim Bouaraba");
//        System.out.println(user.toString());
    }

    @Test(enabled = true, dataProvider = "restapitwitterusers userNames", dataProviderClass = DataProviders.class)
    public void testSearchTweets(String username) {
        String userId = "@" + username;
        ValidatableResponse response = this.tweetAPIClient.searchTweets(userId);
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        String json = response.extract().contentType();
        System.out.println(json);
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    
    @Test(dataProvider = "getTestData", dataProviderClass = DataProviders.class)
    public void TestListOfTweets(String Flights){
        tweetAPIClient.createListTweets(Flights );
        System.out.println(Flights +"-------->>>>>>>"+tweetAPIClient.createListTweets(Flights ));
    }
    
    
    

}
