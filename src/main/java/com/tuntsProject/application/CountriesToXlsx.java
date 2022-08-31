package com.tuntsProject.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CountriesToXlsx {

	public static void countriesToXlsx(String host, String xlsxPath) {
		
		List<Country> countries = GetJsonFromUrl.getCountriesFromApi(host);

		try (XSSFWorkbook workbook = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(new File(xlsxPath));) {

			XSSFSheet sheet = workbook.createSheet("Countries List");
			XSSFRow row;
			createHeader(workbook, sheet);

			for (int i = 0; i < countries.size(); i++) {
				row = sheet.createRow(i + 2);
				Cell cell = row.createCell(0);
				String name = countries.get(i).getName();
				name = name.replace("\"", "");
				cell.setCellValue(name);

				cell = row.createCell(1);
				String capital = countries.get(i).getName();
				capital = capital.replace("\"", "");
				cell.setCellValue(capital);

				cell = row.createCell(2);
				cell.setCellValue(countries.get(i).getArea());

				cell = row.createCell(3);
				String currencies = countries.get(i).getCurrencies();
				if (currencies.length() > 1) {
					currencies = currencies.replace("-", "");
				}
				currencies = currencies.replace("\"\"", ", ");
				currencies = currencies.replace("\"", "");
				cell.setCellValue(currencies);
			}
			// Sizing cells length
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	private static void createHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
		// Setting title font properties
		XSSFFont titleFont = workbook.createFont();
		XSSFColor greyTitle = new XSSFColor(new java.awt.Color(79, 79, 79));
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setFontName("Arial");
		titleFont.setColor(greyTitle);
		titleFont.setBold(true);

		// Setting header font properties
		XSSFFont headerFont = workbook.createFont();
		XSSFColor greyHeader = new XSSFColor(new java.awt.Color(80, 80, 80));
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setFontName("Arial");
		headerFont.setColor(greyHeader);
		headerFont.setBold(true);

		// Title
		XSSFRow row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Countries List");

		// Title style
		CellStyle styleTitle = workbook.createCellStyle();
		styleTitle.setFont(titleFont);
		styleTitle.setAlignment(CellStyle.ALIGN_CENTER);

		// Merging title cells
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		cell.setCellStyle(styleTitle);

		// Header style
		CellStyle styleHeader = workbook.createCellStyle();
		styleHeader.setFont(headerFont);
		styleHeader.setAlignment(CellStyle.ALIGN_LEFT);

		// Header
		row = sheet.createRow(1);

		cell = row.createCell(0);
		cell.setCellValue("Name");
		cell.setCellStyle(styleHeader);

		cell = row.createCell(1);
		cell.setCellValue("Capital");
		cell.setCellStyle(styleHeader);

		cell = row.createCell(2);
		cell.setCellValue("Area");
		cell.setCellStyle(styleHeader);

		cell = row.createCell(3);
		cell.setCellValue("Currencies");
		cell.setCellStyle(styleHeader);
	}
}