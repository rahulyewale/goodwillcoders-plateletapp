package org.ngo.think.dm.service.util;

import java.io.File;

import org.ngo.think.dm.persistence.domain.ExcelTypes;

public class ExcelUtil
{

	public static String validateForExcelFile(File file)
	{
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		String fileExtension = file.getName().substring(dotIndex+1);

		if(ExcelTypes.XLS.getExtension().equals(fileExtension)
				|| ExcelTypes.XLSX.getExtension().equals(fileExtension))
		{
			return fileExtension;
		}
		else
		{
			return null;
		}
	}
}
