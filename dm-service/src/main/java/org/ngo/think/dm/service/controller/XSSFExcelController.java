package org.ngo.think.dm.service.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ngo.think.dm.service.domain.Donor;
import org.springframework.stereotype.Controller;

@Controller
public class XSSFExcelController
{

	private Map<Integer, String> headerRowMap = new HashMap<Integer, String>();

	private List<Donor> donors = new ArrayList<Donor>();

	public List<Donor> getDonors()
	{
		return donors;
	}

	public void readXSSFExcel(InputStream fileStream)
	{
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Start Parse headers i.e. first row
			XSSFRow firstRow = (XSSFRow) rowIterator.next();
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

				XSSFRow row = (XSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					XSSFCell cell = (XSSFCell) cellIterator.next();

					switch(headerRowMap.get(cell.getColumnIndex()))
					{
						case "ID":
							// case 0:
							Double donorId = cell.getNumericCellValue();
							donor.setDonorId(new Long(donorId.longValue()));
							break;
						case "FIRST NAME":
							// case 1:
							donor.setFirstName(cell.getStringCellValue());
							break;
						case "LAST NAME":
							// case 2:
							donor.setLastName(cell.getStringCellValue());
							break;
						case "DOB":
							// case 3:

							donor.setDateOfBirth(cell.getStringCellValue());
							break;
						case "RESI ADDRESS":
							// case 4:
							donor.setResidentialAddress(cell.getStringCellValue());
							break;

						case "PINCODE":
							// case 5:
							// Double doubleValue = cell.getNumericCellValue();
							// Long longValue = new Long(doubleValue.longValue());
							donor.setPincode(cell.getStringCellValue());
							break;

						case "CITY":
							// case 6:
							donor.setCity(cell.getStringCellValue());
							break;

						case "STATE":
							// case 7:
							donor.setState(cell.getStringCellValue());
							break;

						case "CONTACT NUMBER":
							// case 8:
							// Double doubleValueCN = cell.getNumericCellValue();
							// Long longValueCN = new Long(doubleValueCN.longValue());
							donor.setContactNumber(cell.getStringCellValue());
							break;

						case "EMAIL":
							// case 9:
							donor.setEmail(cell.getStringCellValue());
							break;

						case "DONATION FREQ":
							// case 10:
							donor.setDonationFrequency((int) cell.getNumericCellValue());
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

}
