package org.ngo.think.dm.web.constant;

public class WebConstant
{
	static class RestServiceConstant
	{
		private static final String PROTOCOL = "http";
		private static final String HOST = "localhost";
		private static final String PORT = "8800";
		private static final String SERVICE_NAME = "dm-service";
		
	}
	
	static class ServiceName
	{
		private static final String SEARCH_DONOR = "searchdonor";
		private static final String SEARCH_COMMUNICATION_HISTORY = "searchcommunicationhistory";
		private static final String CONFIRM_COMMUNICATION_HISTORY = "confirmcommunicationhistory";
		private static final String IMPORT_DONOR_DATA = "importdonordata";
		private static final String GET_DONATION_CENTERS = "getdonationcenters";
		private static final String GET_REQUEST_LIST = "getrequestlist";
		private static final String ADD_DONATION_HISTORY = "addDonationHistory";

	}
	
	public static class ServiceURL
	{
		private static final String BASE_URL = RestServiceConstant.PROTOCOL + "://" + RestServiceConstant.HOST + ":" + RestServiceConstant.PORT + "/" + RestServiceConstant.SERVICE_NAME + "/";
		public static final String SEARCH_DONOR_SERVICE_URL = BASE_URL + ServiceName.SEARCH_DONOR;
		
		public static final String IMPORT_DONOR_DATA_URL = BASE_URL + ServiceName.IMPORT_DONOR_DATA;
		
		public static final String SEND_SMS_TO_DONORS_SERVICE_URL = BASE_URL + "sendSMSToDonors/";
		
		public static final String GET_DONATION_CENTERS_URL = BASE_URL + ServiceName.GET_DONATION_CENTERS;
		
		public static final String COMMUNICATION_HISTORY_SEARCH_SERVICE_URL = BASE_URL + ServiceName.SEARCH_COMMUNICATION_HISTORY;
		public static final String CONFIRM_COMMUNICATION_HISTORY_URL = BASE_URL + ServiceName.CONFIRM_COMMUNICATION_HISTORY;
		public static final String GET_REQUEST_LIST_URL = BASE_URL + ServiceName.GET_REQUEST_LIST;
		public static final String ADD_DONATION_HISTORY_SERVICE_URL = BASE_URL + ServiceName.ADD_DONATION_HISTORY;
	}
	
}
