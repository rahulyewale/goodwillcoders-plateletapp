package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "requestActionMB")
@SessionScoped
public class RequestActionMB implements Serializable
{

	private static final long serialVersionUID = 1L;

	
	
	public void confirmDonor(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Confirming Donor : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rejectDonor()
	{
		//System.out.println("Rejecting Donor : "+ communicationHistoryResultDTO.getDonorName());
		System.out.println("Called");
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reserveDonor(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Rejerving Donor : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDonationHistory(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Adding Donation History For : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
