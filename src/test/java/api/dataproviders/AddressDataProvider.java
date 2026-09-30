package api.dataproviders;

import org.testng.annotations.DataProvider;

public class AddressDataProvider {

    @DataProvider(name = "addressData")
    public Object[][] addressData() {

        return new Object[][]{
                {"Diamond Harbour", "Home", "India", "iuytrew", "Bangalores", "lkjhgfdsa", "Karnataka", "432104", "Home Address", "9737280329"},
                {"Bangalore", "Office", "India", "Office Building", "MG Road", "Near Metro Station", "Karnataka", "432103", "Office Address", "9737280329"},
                {"Mumbai", "Other", "India", "Building 10", "Andheri Road", "Near Mall", "Maharashtra", "432101", "Mumbai Address", "9737280329"},
                {"Delhi", "Home", "India", "Building 20", "Main Street", "Near Market", "Delhi", "432102", "Delhi Address", "9737280329"}
        };
    }
}