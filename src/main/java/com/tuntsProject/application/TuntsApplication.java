package com.tuntsProject.application;

import java.util.Scanner;

public class TuntsApplication {

	public static void main(String[] args) {
		String host = "https://restcountries.com/v2/all?fields=name,capital,area,currencies";
	
		Scanner sc = new Scanner(System.in);	
		System.out.println("Write the directory path to save the spreadsheet (example: C:\\Users\\yourUser\\Desktop):") ;
		String path = sc.next();
		
		String xlsxPath = path + "\\CountriesList.xlsx";
		
		System.out.println("Please wait...");
		CountriesToXlsx.countriesToXlsx(host, xlsxPath);
		System.out.println("Done! Check the directory path that you provided.");
		sc.close();
	}

}
