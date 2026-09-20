package api.dataproviders;

import org.testng.annotations.DataProvider;

import api.utils.FakeDataGenerator;

public class AdminDataProvider {

    @DataProvider(name = "validAdminData")
    public Object[][] validAdminData() {

        return new Object[][] {
                {
                    FakeDataGenerator.getFirstName(),
                    FakeDataGenerator.getLastName(),
                    FakeDataGenerator.getUniqueEmail(),
                    FakeDataGenerator.getPhoneNumber()
                },
                {
                    FakeDataGenerator.getFirstName(),
                    FakeDataGenerator.getLastName(),
                    FakeDataGenerator.getUniqueEmail(),
                    FakeDataGenerator.getPhoneNumber()
                },
                {
                    FakeDataGenerator.getFirstName(),
                    FakeDataGenerator.getLastName(),
                    FakeDataGenerator.getUniqueEmail(),
                    FakeDataGenerator.getPhoneNumber()
                },
                {
                    FakeDataGenerator.getFirstName(),
                    FakeDataGenerator.getLastName(),
                    FakeDataGenerator.getUniqueEmail(),
                    FakeDataGenerator.getPhoneNumber()
                }
        };
    }
}