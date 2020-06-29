package base;
import dataBase.ConnectToSqlDB;
import utility.DataReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;

public class DataProviders extends ConnectToSqlDB {
//     @DataProvider(name = "TRY usernames")
//     public static Object[][] readSQL() throws Exception {
//         ArrayList<String> lst = (ArrayList<String>) readDataBase("TRY", "usernames");
//         return lst.stream()
//                 .map(student -> new Object[] { student })
//                 .toArray(Object[][]::new);
// }
    @DataProvider (name="restapitwitterusers userNames" )
    public static Object[][] readSQL() throws Exception {
        ArrayList<String> lst = (ArrayList<String>) readDataBase("restapitwitterusers", "userNames");
        return lst.stream()
                .map(student -> new Object[]{student})
                .toArray(Object[][]::new);
    }
    @DataProvider
    public Object[][] getTestData() throws IOException, InvalidFormatException {
        Object data1 [][]= DataReader.fileReader3("UnitedSheet","C:\\Users\\lamar\\Desktop\\Selenium\\UnitedList.xlsx");
        return data1;
    }
    
}
