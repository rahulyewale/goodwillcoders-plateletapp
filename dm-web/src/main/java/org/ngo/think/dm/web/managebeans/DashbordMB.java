package org.ngo.think.dm.web.managebeans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "dashbord")
public class DashbordMB {

	public String navigateToSearchDonor() {
		// fetching the center list
		System.out.println("navigating to search donor");
		return "success";
	}
}