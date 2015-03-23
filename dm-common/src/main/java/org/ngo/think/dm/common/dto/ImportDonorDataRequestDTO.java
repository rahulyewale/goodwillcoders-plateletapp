package org.ngo.think.dm.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportDonorDataRequestDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String fileName;

	private byte[] excelByteArray;

	public byte[] getExcelByteArray()
	{
		return excelByteArray;
	}

	public void setExcelByteArray(byte[] excelByteArray)
	{
		this.excelByteArray = excelByteArray;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

}
