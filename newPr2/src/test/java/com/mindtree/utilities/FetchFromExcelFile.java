package com.mindtree.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class FetchFromExcelFile
{
	public String fetchCellData(int row, char col)
	{
		LoggingLevels l = new LoggingLevels();
		String s = "";
		try
		{
			FileInputStream fs = new FileInputStream("./Excel Files/Data2.xls");
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			int k = wb.getNumberOfSheets();
			for (int i = 0; i < k; i++)
			{
				if (wb.getSheetName(i).equals("Sheet1"))
				{
					HSSFSheet sheet = wb.getSheetAt(i);
					Iterator<Row> itr = sheet.iterator();
					int ro = 1;
					while (itr.hasNext())
					{
						char co = 'A';
						Row r = itr.next();
						Iterator<Cell> itc = r.iterator();
						while (itc.hasNext())
						{
							Cell c = itc.next();
							if (ro == row && co == col)
							{
								s = c.getStringCellValue(); // s = NumberToTextConverter.toText(c.getNumericCellValue());
															// c.getCellTypeEnum() == (CellType.STRING)
							}
							co++;
						}
						ro++;
					}
				}
			}
		}
		catch (FileNotFoundException f)
		{
			l.error("Specified excel file not found");
			f.getMessage();
		}
		catch (IOException e)
		{
			l.error("Could not load the excel file");
			e.getMessage();
		}
		return s;
	}
}
