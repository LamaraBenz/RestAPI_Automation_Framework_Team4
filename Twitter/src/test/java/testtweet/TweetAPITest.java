package testtweet;


import base.DataProviders;
import base.TwitterAPIClient;
import datarideur.DataReader;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.io.IOException;
import java.util.UUID;

//import org.testng.annotations.BeforeClass;

public class TweetAPITest extends TwitterAPIClient {
    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @org.testng.annotations.AfterTest


    public void testUserCanTweetSuccessfully(){
        // 1. User send a tweet
        String tweet="karim First twet "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was successfully send
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");

        System.out.println(response.extract().time());
        Assert.assertEquals(tweet,actualTweet);
    }
    @DataProvider
    public Object[][] getTestData() throws IOException, InvalidFormatException {
        Object data1 [][]= DataReader.fileReader3("UnitedSheet","/Users/karimmekdoud/Desktop/UnitedList.xlsx");

        return data1;
    }
    @Test(dataProvider = "getTestData")
    public void TestListOfTweets(String Flights){
        tweetAPIClient.createListTweets(Flights );
        System.out.println(Flights);
    }
    @Test
    public void TestgetUsermentionstimeline(){
        tweetAPIClient.getUsermentionstimeline();
        System.out.println(tweetAPIClient.getUsermentionstimeline());
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
//        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet).assertThat()
//                .body("id",equals());

        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1273434027597484038L);
    }
   @Test
    public void TestgetUserSearch(){
       tweetAPIClient.getUserSearch("Hakim Bouaraba");
       ValidatableResponse response=this.tweetAPIClient.getUserSearch("Hakim Bouaraba");
       response.statusCode(200);
       ValidatableResponse user = tweetAPIClient.getUserSearch("Hakim Bouaraba");
       System.out.println(user.toString());
   }

    @Test(enabled = true,dataProvider = "twitterUseres TwitterUseresNames", dataProviderClass = DataProviders.class)
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


}
