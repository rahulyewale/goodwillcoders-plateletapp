package org.ngo.think.dm.web.managebeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "dashbord")
@RequestScoped
public class DashbordMB
{

	public String navigateToSearchDonor()
	{
		// fetching the center list
		System.out.println("navigating to search donor");
		return "success";
	}

	public String navigateToDashbord()
	{
		// fetching the center list
		System.out.println("navigating to dashbord");
		return "success";
	}

	public String navigateToSearchCommunicationHistory()
	{
		System.out.println("navigating to search communication history");
		return "success";
	}
	
	public String navigateToExcelUpload()
	{
		return "success";
	}
	
}
