package com.anoop.thirteen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class App {

	private static XSSFWorkbook wb =null;
	private static XSSFSheet sheet =null;
	private static String cellValue = null;

	public static XSSFWorkbook loadWorkBook(File fileName) throws Exception{
		try{
	        FileInputStream flStream = new FileInputStream(fileName);
	         wb = new XSSFWorkbook(flStream);
		}catch(Exception e){
			System.out.println("Workbook not loaded");
			throw e;
		}
		return wb;
	}

	public static String getCellValueByColumnName(String ColumnName, int rowNum)
			throws IOException {
		DataFormatter formatter = new DataFormatter();
		try {
			sheet = wb.getSheetAt(0);
			// it will take value from first row
			Row row = sheet.getRow(0);
			// it will give you count of row which is used or filled
			short lastcolumnused = row.getLastCellNum();

			int colnum = 0;
			for (int i = 0; i < lastcolumnused; i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(ColumnName)) {
					colnum = i;
					break;
				}
			}
			row = sheet.getRow(rowNum);
			cellValue = formatter.formatCellValue(row.getCell(colnum));
		}catch(Exception e){
			System.out.println(e);
		}
		return cellValue;
	}

	
	public static List<String> loadColumnNames()
			throws Exception {
		List<String> columnNamesList = new ArrayList<String>();
		try {
			sheet = wb.getSheetAt(0);
			// it will take value from first row
			Row row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				columnNamesList.add(row.getCell(i).getStringCellValue());
				
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return columnNamesList;
	}

	
	public static void main(String[] args) {
		/*
		 * List(model object) created
		 */
		List<ModelTable> modelTables = new ArrayList<ModelTable>();
		File file =    new File("./documents/excel.xlsx");
		List<String> columnNamesList = new ArrayList<String>();
		
		try {
			wb = loadWorkBook(file);
			columnNamesList = loadColumnNames();
			System.out.println("columnNamesList::"+columnNamesList);
			
			try {
				for(int rowNum= 1; rowNum <= wb.getSheetAt(0).getLastRowNum();rowNum++){
                       /*
                        * List(row) created
                        */
					List<Object> rowList = new ArrayList<Object>();
					for(String column: columnNamesList){
						String cellValue = getCellValueByColumnName(column, rowNum);
						/*
						 * List(row) used
						 */
						rowList.add(cellValue);
					}
					/*
					 * Model object created & used
					 */
					ModelTable modelTable = new ModelTable();
					int k=0;
					modelTable.setSno(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setTransactionID(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setTransactionDate(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setMethod(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setPaymentData(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setPaymentStatus(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setSsoId(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setFirstName(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setLastNameAddress(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setAddress(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setCity(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setState(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setZip(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setCountry(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setMobile(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setEmail(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setCurrency(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setAmount(rowList.get(k)!=null? rowList.get(k):null); k++;
					modelTable.setPackageType(rowList.get(k)!=null? rowList.get(k):null);
					/*
					 * List(model object) used
					 */
					modelTables.add(modelTable);	
				}
				/*
				 * Printing List(model object)
				 */
				modelTables.stream().forEach(o->{System.out.println(o);});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
						
		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
	}

}
