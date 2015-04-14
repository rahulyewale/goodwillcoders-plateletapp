package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;

/**
 * Simple navigation bean
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable
{

	private static final long serialVersionUID = 1520318172495977648L;

	@ManagedProperty(value = "#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	/**
	 * Redirect to login page.
	 * @return Login page name.
	 */
	public String redirectToLogin()
	{
		return "/login.xhtml?faces-redirect=true";
	}

	/**
	 * Go to login page.
	 * @return Login page name.
	 */
	public String toLogin()
	{
		return "/login.xhtml";
	}

	/**
	 * Redirect to info page.
	 * @return Info page name.
	 */
	public String redirectToInfo()
	{
		return "/info.xhtml?faces-redirect=true";
	}

	/**
	 * Go to info page.
	 * @return Info page name.
	 */
	public String toInfo()
	{
		return "/info.xhtml";
	}

	/**
	 * Redirect to welcome page.
	 * @return Welcome page name.
	 */
	public String redirectToWelcome()
	{
		searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
		searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
		searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());
		return "/secured/searchdonor.xhtml?faces-redirect=true";
	}

	/**
	 * Go to welcome page.
	 * @return Welcome page name.
	 */
	public String toWelcome()
	{
		return "/secured/dashboard.xhtml";
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
