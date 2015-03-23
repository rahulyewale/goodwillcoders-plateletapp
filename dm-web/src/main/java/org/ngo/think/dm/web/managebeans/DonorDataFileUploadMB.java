package org.ngo.think.dm.web.managebeans;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.ImportDonorDataRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadMB")
@SessionScoped
public class DonorDataFileUploadMB
{

	public DonorDataFileUploadMB()
	{
	}

	private UploadedFile file;

	public UploadedFile getFile()
	{
		return file;
	}

	public void setFile(UploadedFile file)
	{
		this.file = file;
	}

	public void upload()
	{

		if (file != null)
		{

			System.out.println(file.getFileName());
			ServiceRequest serviceRequest = null;
			try
			{
				
				byte[] excelByteArray = IOUtils.toByteArray(file.getInputstream());
				ImportDonorDataRequestDTO importDonorDataRequestDTO = new ImportDonorDataRequestDTO();
				
				importDonorDataRequestDTO.setExcelByteArray(excelByteArray);
				importDonorDataRequestDTO.setFileName(file.getFileName());
				
				serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.IMPORT_DONOR_EXCEL_REQUEST, importDonorDataRequestDTO);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			ServiceResponse serviceResponse = null;
			try
			{
				serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.IMPORT_DONOR_DATA_URL, serviceRequest);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}

			SearchDonorResponseDTO responseDTO = null;

			String responseString;
			try
			{
				responseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_DONOR_RESPONSE));
				responseDTO = (SearchDonorResponseDTO) JsonUtil.convertJsonToObject(responseString, SearchDonorResponseDTO.class);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}