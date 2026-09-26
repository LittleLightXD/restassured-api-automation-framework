package api.utils;

import api.payload.ProductPayload;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getProductData(String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();

            Object[][] data = new Object[rowCount - 1][2];

            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);

                String productKey = getCellValue(row.getCell(0));
                String productName = getCellValue(row.getCell(1));
                String price = getCellValue(row.getCell(2));
                String discountPrice = getCellValue(row.getCell(3));
                String quantity = getCellValue(row.getCell(4));
                String category = getCellValue(row.getCell(5));
                String subcategory = getCellValue(row.getCell(6));
                String brand = getCellValue(row.getCell(7));
                String description = getCellValue(row.getCell(8));
                String productImage = getCellValue(row.getCell(9));

                ProductPayload payload = new ProductPayload();

                payload.setProductName(productName);
                payload.setPrice(Double.parseDouble(price));
                payload.setDiscountPrice(Double.parseDouble(discountPrice));
                payload.setQuantity(Integer.parseInt(quantity));
                payload.setCategory(category);
                payload.setSubcategory(subcategory);
                payload.setBrand(brand);
                payload.setDescription(description);
                payload.setProductImage(productImage);

                payload.setMerchantId(TestData.merchantId);
                payload.setRating(4.5);
                payload.setReviewCount(0);
                payload.setInStock(true);

                data[i - 1][0] = productKey;
                data[i - 1][1] = payload;
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Unable to read product Excel file", e);
        }
    }

    private static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell);
    }
}