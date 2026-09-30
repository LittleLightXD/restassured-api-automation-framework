package api.dataproviders;

import api.utils.ExcelReader;
import org.testng.annotations.DataProvider;

public class ReviewDataProvider {

    @DataProvider(name = "reviewData")
    public Object[][] reviewData() {

        String filePath = "src/test/resources/ReviewData.xlsx";
        String sheetName = "CreateReview";

        return ExcelReader.getExcelData(
                filePath,
                sheetName
        );
    }
}