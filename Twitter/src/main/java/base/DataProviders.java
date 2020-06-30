package base;

import dataBase.ConnectToSqlDB;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

public class DataProviders extends ConnectToSqlDB {

   @DataProvider(name="twitterUseres TwitterUseresNames")
    public static Object[][] readSQL() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("twitterUseres", "TwitterUseresNames");
        return lst.stream()
                .map(student -> new Object[] { student })
                .toArray(Object[][]::new);
}
}
