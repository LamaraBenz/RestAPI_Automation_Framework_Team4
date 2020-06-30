package base;

import dataBase.ConnectToSqlDB;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.DataReader;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviders extends ConnectToSqlDB {

   @DataProvider(name="RestApiUsers usernames")
    public static Object[][] readSQL() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("RestApiUsers", "usernames");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);
    }
String path = "C:/Users/ssbra/Desktop/Excel_files_Project/UnitedList.xlsx";
    @DataProvider
    public Object[][] getTestData() throws IOException, InvalidFormatException {
        Object data1 [][]= DataReader.fileReader3("UnitedSheet",path);

        return data1;
    }


}