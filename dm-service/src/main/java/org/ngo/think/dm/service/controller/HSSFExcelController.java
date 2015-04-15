package org.ngo.think.dm.service.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ngo.think.dm.service.domain.Donor;
import org.springframework.stereotype.Controller;

@Controller
public class HSSFExcelController
{

	private Map<Integer, String> headerRowMap = new HashMap<Integer, String>();

	private List<Donor> donors = new ArrayList<Donor>();

	public void readHSSFExcel(InputStream fileStream)
	{
		headerRowMap = new HashMap<Integer, String>();
		donors = new ArrayList<Donor>();
		
		try
		{
			HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			//Start Parse headers i.e. first row
			HSSFRow firstRow = (HSSFRow)rowIterator.next();
			Iterator<Cell> firstRowCellIterator = firstRow.cellIterator();
			while (firstRowCellIterator.hasNext())
			{
				Cell firstRowCell = firstRowCellIterator.next();
				headerRowMap.put(firstRowCell.getColumnIndex(), firstRowCell.getStringCellValue());
			}
			//End

			//Parse remaining rows
			while (rowIterator.hasNext())
			{
				Donor donor = new Donor();

				HSSFRow row = (HSSFRow)rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					HSSFCell cell = (HSSFCell) cellIterator.next();

					//rowData.put(headerRowMap.get(cell.getColumnIndex()), cell);
					switch (headerRowMap.get(cell.getColumnIndex()))
					//switch (cell.getColumnIndex())
					{
						case "ID":
						//case 0:
							Double donorId = cell.getNumericCellValue();
							donor.setDonorId(new Long(donorId.longValue()));
							break;
						case "FIRST NAME":
						//case 1:
							donor.setFirstName(cell.getStringCellValue());
							break;
						case "LAST NAME":
						//case 2:
							donor.setLastName(cell.getStringCellValue());
							break;
						case "DOB":
						//case 3:

							donor.setDateOfBirth(cell.getStringCellValue());
							break;
						case "RESI ADDRESS":
						//case 4:
							donor.setResidentialAddress(cell.getStringCellValue());
							break;

						case "PINCODE":
						//case 5:
							Double doubleValue = cell.getNumericCellValue();
							Long longValue = new Long(doubleValue.longValue());
							donor.setPincode(longValue.toString());
							break;

						case "CITY":
						//case 6:
							donor.setCity(cell.getStringCellValue());
							break;

						case "STATE":
						//case 7:
							donor.setState(cell.getStringCellValue());
							break;

						case "CONTACT NUMBER":
						//case 8:
							//Double doubleValueCN = cell.getNumericCellValue();
							//Long longValueCN = new Long(doubleValueCN.longValue());
							donor.setContactNumber(cell.getStringCellValue());
							break;

						case "EMAIL":
						//case 9:
							donor.setEmail(cell.getStringCellValue());
							break;

						case "DONATION FREQ":
						//case 10:
							donor.setDonationFrequency((int)cell.getNumericCellValue());
							break;
						default:
							break;
					}
				}
				donors.add(donor);
			}
			fileStream.close();
		}
		catch (Exception e)
		{
			System.out.println("Error occurred while parsing excel file.");
			e.printStackTrace();
		}
	}
	
	public void readHSSFExcel(InputStream fileStream, boolean ngoFileFormat)
	{
		headerRowMap = new HashMap<Integer, String>();
		donors = new ArrayList<Donor>();
		
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(fileStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Start Parse headers i.e. first row
			HSSFRow firstRow = (HSSFRow) rowIterator.next();
			Iterator<Cell> firstRowCellIterator = firstRow.cellIterator();
			
			while (firstRowCellIterator.hasNext())
			{
				Cell firstRowCell = firstRowCellIterator.next();
				headerRowMap.put(firstRowCell.getColumnIndex(), firstRowCell.getStringCellValue());
			}
			// End
			System.out.println(headerRowMap);

			// Parse remaining rows
			while (rowIterator.hasNext())
			{
				Donor donor = new Donor();

				HSSFRow row = (HSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					HSSFCell cell = (HSSFCell) cellIterator.next();

					System.out.println("Processing row:"+row.getRowNum()+" Processing cell index:"+cell.getColumnIndex());
					System.out.println(headerRowMap.get(cell.getColumnIndex()));
					switch(headerRowMap.get(cell.getColumnIndex()).trim())
					{
						
						case "Name":
							donor.setFirstName(cell.getStringCellValue());
							break;
						case "Blood Group":
							String bloodGroup = cell.getStringCellValue();
							if(StringUtils.isNotEmpty(bloodGroup))
							{
								bloodGroup = bloodGroup.trim();
								bloodGroup = bloodGroup.substring(0, 2);
							}
							donor.setBloodGroup(bloodGroup.toUpperCase());
							break;
						case "Mobile No.":
							donor.setContactNumber(cell.getStringCellValue());
							break;
						case "Residence Address/Pincode":
							donor.setResidentialAddress(cell.getStringCellValue());
							break;

						case "Residence pincode":
							donor.setPincode(cell.getStringCellValue());
							break;
						case "State":
							donor.setState(cell.getStringCellValue());
							break;
						default:
							try
							{
								System.out.println("default:"+cell.getStringCellValue());
							}
							catch(Exception eS)
							{
								
							}
							break;
					}
				}
				donors.add(donor);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error occurred while parsing excel file.");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				workbook.close();
				fileStream.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public List<Donor> getDonors()
	{
		return donors;
	}
	
	
}
