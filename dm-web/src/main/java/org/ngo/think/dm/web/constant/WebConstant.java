package org.ngo.think.dm.web.constant;

import org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping;

public class WebConstant
{
	static class RestServiceConstant
	{
		private static final String PROTOCOL = "http";
		private static final String HOST = "localhost";
		private static final String PORT = "8800";
		private static final String SERVICE_NAME = "dm-service";
		
	}
	
	public static class ServiceURL
	{
		private static final String BASE_URL = RestServiceConstant.PROTOCOL + "://" + RestServiceConstant.HOST + ":" + RestServiceConstant.PORT + "/" + RestServiceConstant.SERVICE_NAME + "/";
		public static final String SEARCH_DONOR_SERVICE_URL = BASE_URL + ServiceRequestMapping.SEARCH_DONOR;
		
		public static final String IMPORT_DONOR_DATA_URL = BASE_URL + ServiceRequestMapping.IMPORT_DONOR_DATA;
		
		public static final String SEND_SMS_TO_DONORS_SERVICE_URL = BASE_URL + ServiceRequestMapping.SEND_SMS;
		
		public static final String GET_DONATION_CENTERS_URL = BASE_URL + ServiceRequestMapping.GET_DONATION_CENTERS;
		
		public static final String COMMUNICATION_HISTORY_SEARCH_SERVICE_URL = BASE_URL + ServiceRequestMapping.SEARCH_COMMUNICATION_HISTORY;
		public static final String CONFIRM_COMMUNICATION_HISTORY_URL = BASE_URL + ServiceRequestMapping.CONFIRM_COMMUNICATION_HISTORY;
		public static final String GET_REQUEST_LIST_URL = BASE_URL + ServiceRequestMapping.GET_REQUEST_LIST;
		public static final String ADD_DONATION_HISTORY_SERVICE_URL = BASE_URL + ServiceRequestMapping.ADD_DONATION_HISTORY;
		
		public static final String GET_FEATURES_SERVICE_URL = BASE_URL + ServiceRequestMapping.GET_FEATURES;
		public static final String CLOSE_REQUEST_URL = BASE_URL + ServiceRequestMapping.CLOSE_REQUEST;
		public static final String WITHDRAW_REQUEST_URL = BASE_URL + ServiceRequestMapping.WITHDRAW_REQUEST;
	}
	
}
