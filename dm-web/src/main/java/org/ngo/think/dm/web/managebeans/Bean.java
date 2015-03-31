package org.ngo.think.dm.web.managebeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;

@ManagedBean(name = "bean")
@RequestScoped
public class Bean
{
	@ManagedProperty(value="#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	private String page = "searchdonor";
	
	private int currentTab=0;

	public int getCurrentTab()
	{
		return currentTab;
	}

	public void setCurrentTab(int currentTab)
	{
		this.currentTab = currentTab;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}
	
	public void setPageInfo(String page,int currentTab)
	{
		this.page = page;
		this.currentTab=currentTab;
		
		if(page.equals("searchdonor"))
		{
			searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
			searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
			searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

		}
		
	}

	public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}
	
	
	
	
	
	}
