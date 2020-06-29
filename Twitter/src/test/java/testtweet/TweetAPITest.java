package testtweet;
import base.DataProviders;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;
import utility.DataReaderTeam4;
import java.io.IOException;
import java.util.UUID;
public class TweetAPITest {
    private TweetAPIClient tweetAPIClient;
    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }
    @Test
    // public void testUserCanTweetSuccessfully(){
    //     String tweet="Azul Team4 "+ UUID.randomUUID().toString();
//    @org.testng.annotations.AfterTest
    public void testUserCanTweetSuccessfully(){
        // 1. User send a tweet
        String tweet="LAMARA"+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    // Write an API test where user can not tweet the same tweet twice in a row
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        String tweet="Azul Team4  "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        response=this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        String expectedMessage="Status is a duplicate.";
        String actualMessage=response.extract().body().path("errors[0].message");
             Assert.assertEquals(actualMessage,expectedMessage);
    }
//---Achours
    // @Test
    // public void testDeleteTweet(){
    //     String tweet="Tweet "+ UUID.randomUUID().toString();
    //     ValidatableResponse response=this.tweetAPIClient.deleteTweet(1277120862307655682L);
    //     ValidatableResponse response1=this.tweetAPIClient.deleteTweet(1277120858008498176L); }

    // @Test(enabled = true, dataProvider = "TRY usernames", dataProviderClass = DataProviders.class)
    // public void testSearchTweets(String username) {
    //     String userId = "@" + username;
    //     ValidatableResponse response = this.tweetAPIClient.searchTweets(userId);
    //     ResponseBody body = (ResponseBody) response.extract().body();
    //     System.out.println("Response Body is: " + body.asString());
    //     String json = response.extract().contentType();
    //     System.out.println(json);
    //     int actualResponseCode = response.extract().statusCode();
    //     int expectedResponseCode = 200;
    //     Assert.assertEquals(actualResponseCode, expectedResponseCode); }

    // @DataProvider
    // public Object[] getTestData() throws IOException, InvalidFormatException {
    //     Object[] data = DataReaderTeam4.fileReader3("sheet1","/Users/ashorouali/Desktop/restapi.xlsx");
    //     return data;}
    // @Test (dataProvider="getTestData")
    // public void  testADDFAVORITESENDPOINT(String username){
    //     String userId = "@" + username;
    //     ValidatableResponse response = this.tweetAPIClient.ADDFAVORITESENDPOINT(userId);
    //     ResponseBody body = (ResponseBody) response.extract().body();
    //     System.out.println("Response Body is: " + body.asString());
    //     String json = response.extract().contentType();
    //     System.out.println(json);
    //     int actualResponseCode = response.extract().statusCode();
    //     int expectedResponseCode = 200;
    //     Assert.assertEquals(actualResponseCode, expectedResponseCode); }

    // @DataProvider
    // public Object[] getTestData1() throws IOException, InvalidFormatException {
    //     Object[] data = DataReaderTeam4.fileReader3("sheet1","/Users/ashorouali/Desktop/city.xlsx");
    //     return data;}
    // @Test (dataProvider="getTestData1")
    // public void  testGETPLACESNEARENDPOINT(String  username){
    //     String userId = "@" + username;
    //     ValidatableResponse response = this.tweetAPIClient.ADDFAVORITESENDPOINT(userId);
    //     ResponseBody body = (ResponseBody) response.extract().body();
    //     String json = response.extract().contentType();
    //     int actualResponseCode = response.extract().statusCode();
    //     int expectedResponseCode = 200;
    //     Assert.assertEquals(actualResponseCode, expectedResponseCode);}
   
//------LAMARAs-------
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
