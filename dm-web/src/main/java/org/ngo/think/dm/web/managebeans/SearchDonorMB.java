package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@SuppressWarnings("serial")
@ManagedBean(name="searchDonorMB")
@SessionScoped
public class SearchDonorMB implements Serializable {
	private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();

		public SearchDonorMB() {
			// TODO Auto-generated constructor stub
		} 
		 
		public void searchDonor()
		{
			DonorDTO donorDTO = new DonorDTO();
			donorDTO.setFirstName("Rajiv");
			donorDTO.setLastName("Vaidya");
			this.getSearchDonorList().clear();
			this.getSearchDonorList().add(donorDTO);
			
			
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.ServiceRequestKey.SEARCH_DONOR_REQUEST,donorRequestDTO);
			ServiceResponse serviceResponse = null;
			try
			{
				serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.SEARCH_DONOR_SERVICE_URL, serviceRequest);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//SearchDonorRequestDTO
		/*	ListContainer listContainer = null;
			String responseString;
			try
			{
				responseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstant.ServiceResponseKey.SEARCH_DONOR_RESPONSE));
				listContainer = (ListContainer) JsonUtil.convertJsonToObject(responseString, ListContainer.class);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			searchDonorList = listContainer.getDonorDTOlist();*/
			
			System.out.println("Search submitted");
			System.out.println(donorRequestDTO.toString());
			System.out.println(getSearchDonorList().size()+" "+getSearchDonorList().get(0).getFirstName());
		}
		
		public void sendSMS()
		{
			System.out.println("generate register id R_32413");
			System.out.println("sending sms ");
		}
		
		public void confirmedDonor()
		{
			System.out.println("confirm donor");
		}

		public List<DonorDTO> getSearchDonorList() {
			
			return searchDonorList;
		}

		public void setSearchDonorList(List<DonorDTO> searchDonorList) {
			this.searchDonorList = searchDonorList;
		}

		public SearchDonorRequestDTO getDonorRequestDTO() {
			return donorRequestDTO;
		}

		public void setDonorRequestDTO(SearchDonorRequestDTO donorRequestDTO) {
			this.donorRequestDTO = donorRequestDTO;
		}
		
	
}
