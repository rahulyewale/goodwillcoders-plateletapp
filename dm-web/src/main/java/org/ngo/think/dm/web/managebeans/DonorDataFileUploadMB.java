package org.ngo.think.dm.web.managebeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorAddressDetailsDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.ImportDonorDataRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadMB")
@RequestScoped
public class DonorDataFileUploadMB
{
	
	private String name;
	private String bloodGroup;
	private String mob;
	private String homeAdd;
	private String pincode;
	private String state;
	private String officeAdd; 
	
	private DonorDTO newDonorDTO = new DonorDTO();
	

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

			FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	public void addDonor()
	{
		
		newDonorDTO.setFirstName(name);
		newDonorDTO.setBloodGroup(bloodGroup);
		DonorAddressDetailsDTO newDonorAddressDetailsDTO = new DonorAddressDetailsDTO();
		newDonorAddressDetailsDTO.setAddressLine1(homeAdd);
		newDonorAddressDetailsDTO.setPinCode(pincode);
		newDonorAddressDetailsDTO.setState(state);
		newDonorDTO.setDonorAddressDetailsDTO(newDonorAddressDetailsDTO);
		
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.ADD_DONOR, newDonorDTO);
		ServiceResponse serviceResponse = null;
		
		System.out.println("Name = "+name+" Blood "+bloodGroup);
		
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.ADD_DONOR_SERVICE_URL, serviceRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getName()
	{
		return name;         	
	}
	public String getBloodGroup()
	{	
		return bloodGroup;   	
	}
	public String getMob()
	{
		return mob;          	
	}
	public String getHomeAdd()
	{	
		return homeAdd;      	
	}
	public String getPincode()
	{
		return pincode;      	
	}
	public String getState()
	{
		return state;        	
	}
	public String getOfficeAdd()
	{	
		return officeAdd;	
	}
	
	public void setName(String name)
	{
		this.name=name;         	
	}
	public void setBloodGroup(String bloodGroup)
	{	
		this.bloodGroup=bloodGroup;   	
	}
	public void setMob(String mob)
	{
		this.mob=mob;          	
	}
	public void setHomeAdd(String homeAdd)
	{	
		this.homeAdd=homeAdd;      	
	}
	public void setPincode(String pincode)
	{
		this.pincode=pincode;      	
	}
	public void setState(String state)
	{
		this.state=state;        	
	}
	public void setOfficeAdd(String officeAdd)
	{	
		this.officeAdd=officeAdd;	
	}
	
	
    
    
    
    
	
	
}