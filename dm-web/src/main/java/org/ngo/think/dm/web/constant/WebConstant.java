package org.ngo.think.dm.web.constant;

public class WebConstant
{
	static class RestServiceConstant
	{
		private static final String PROTOCOL = "http";
		private static final String HOST = "localhost";
		private static final String PORT = "8085";
		private static final String SERVICE_NAME = "dm-service";
		
	}
	
	static class ServiceName
	{
		private static final String SEARCH_DONOR = "searchdonor";
		private static final String SEARCH_COMMUNICATION_HISTORY = "searchcommunicationhistory";
	}
	
	public static class ServiceURL
	{
		private static final String BASE_URL = RestServiceConstant.PROTOCOL + "://" + RestServiceConstant.HOST + ":" + RestServiceConstant.PORT + "/" + RestServiceConstant.SERVICE_NAME + "/";
		public static final String SEARCH_DONOR_SERVICE_URL = BASE_URL + ServiceName.SEARCH_DONOR;
		public static final String COMMUNICATION_HISTORY_SEARCH_SERVICE_URL = BASE_URL + ServiceName.SEARCH_COMMUNICATION_HISTORY;

	}
	
}
