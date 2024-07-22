package com.fountane.amazon.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excelutils 
{
	public static List<Object[]> getTestData(String filePath, String sheetName) {
        List<Object[]> testData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int cellCount = row.getPhysicalNumberOfCells();
                Object[] data = new Object[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    Cell cell = row.getCell(i);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i] = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            data[i] = cell.getBooleanCellValue();
                            break;
                        default:
                            data[i] = null;
                    }
                }
                testData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

}
