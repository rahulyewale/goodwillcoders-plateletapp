package org.ngo.think.dm.persistence.domain;

public enum ExcelTypes
{
	XLS("xls"),
	XLSX("xlsx");

	private String excelExtension;

	private ExcelTypes(String excelExtension)
	{
		this.excelExtension = excelExtension;
	}

	public String getExtension()
	{
		return excelExtension;
	}

	@Override
	public String toString()
	{
		return excelExtension;
	}
}
