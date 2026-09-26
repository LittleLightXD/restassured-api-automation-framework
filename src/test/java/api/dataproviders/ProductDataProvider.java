package api.dataproviders;

import api.utils.ExcelReader;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "productData")
    public Object[][] productData() {

        return ExcelReader.getProductData(
                "src/test/java/api/resources/ProductData.xlsx",
                "Products"
        );
    }
}