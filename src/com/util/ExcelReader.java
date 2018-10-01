package com.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	public String[][] getCellData(String path, String sheet) {

		String data[][] = null;

		try {
			FileInputStream stream = new FileInputStream(path);
			Workbook workbook = WorkbookFactory.create(stream);
			Sheet s = workbook.getSheet(sheet);
			int rowcount = s.getLastRowNum();
			int cellcount = s.getRow(0).getLastCellNum();
			data = new String[rowcount + 1][cellcount];
			for (int i = 0; i <= rowcount; i++) {
				Row r = s.getRow(i);
				for (int j = 0; j < cellcount; j++) {
					Cell c = r.getCell(j);
					data[i][j] = c.getStringCellValue();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
