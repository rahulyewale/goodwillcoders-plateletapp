package org.ngo.think.dm.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.ngo.think.dm.common.dto.ImportDonorDataRequestDTO;
import org.ngo.think.dm.common.enums.RandomNumberType;
import org.ngo.think.dm.common.util.RandomNumberGenerator;
import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.domain.ExcelTypes;
import org.ngo.think.dm.service.controller.HSSFExcelController;
import org.ngo.think.dm.service.controller.XSSFExcelController;
import org.ngo.think.dm.service.domain.Donor;
import org.ngo.think.dm.service.mapper.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ImportDonorDataService
{


	@Autowired
	private HSSFExcelController hssfExcelController;
	
	
	@Autowired
	private XSSFExcelController xssfExcelController ;
	
	@Autowired
	private DonorDAO donorDAO;
	

	@Transactional
	public ImportDonorDataRequestDTO importDonorData(ImportDonorDataRequestDTO importDonorDataRequestDTO) throws Exception
	{
		List<Donor> donorList = null;
		
		String fileExtension = importDonorDataRequestDTO.getFileName().substring(importDonorDataRequestDTO.getFileName().indexOf('.') + 1);

		if (fileExtension == null)
		{
			System.out.println("Program terminated due to incorrect file type.");
			return null;
		}

		InputStream fileStream = new ByteArrayInputStream(importDonorDataRequestDTO.getExcelByteArray());

		try
		{
			if (ExcelTypes.XLS.getExtension().equals(fileExtension))
			{
				hssfExcelController.readHSSFExcel(fileStream);
				donorList = hssfExcelController.getDonors();
			}
			else if (ExcelTypes.XLSX.getExtension().equals(fileExtension))
			{
				xssfExcelController.readXSSFExcel(fileStream);
				donorList = xssfExcelController.getDonors();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		List<org.ngo.think.dm.persistence.entity.Donor> donorEntityList = DonorMapper.toEntityList(donorList);
		
		for(org.ngo.think.dm.persistence.entity.Donor donor : donorEntityList )
		{
			String donorUuid = RandomNumberGenerator.generateRandomNumber(RandomNumberType.DONOR_UUID);
			donor.setDonorUuid(donorUuid);
			donorDAO.save(donor);
		}
		
		
		return importDonorDataRequestDTO;

	}
}
