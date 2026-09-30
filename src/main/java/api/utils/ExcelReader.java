package api.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] getExcelData(String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName
                );
            }

            // Find actual number of columns from header
            Row headerRow = sheet.getRow(0);

            int columnCount = 0;

            for (Cell cell : headerRow) {

                String value = getCellValue(cell);

                if (!value.isBlank()) {
                    columnCount++;
                }
            }

            // Store only rows which actually contain data
            List<Object[]> rows = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null || isEmptyRow(row, columnCount)) {
                    continue;
                }

                Object[] dataRow = new Object[columnCount];

                for (int j = 0; j < columnCount; j++) {
                    dataRow[j] = getCellValue(row.getCell(j));
                }

                rows.add(dataRow);
            }

            return rows.toArray(new Object[0][]);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath,
                    e
            );
        }
    }

    private static boolean isEmptyRow(Row row, int columnCount) {

        for (int i = 0; i < columnCount; i++) {

            if (!getCellValue(row.getCell(i)).isBlank()) {
                return false;
            }
        }

        return true;
    }

    private static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell).trim();
    }
}