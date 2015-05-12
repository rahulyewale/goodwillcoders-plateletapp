package org.ngo.think.dm.common.constant;

public class CommonConstants
{
	public static class RequestKey
	{
		public static final String SEARCH_DONOR_REQUEST = "searchDonorRequest";
		public static final String SEND_SMS_REQUEST = "sendSMSRequest";

		public static final String DONOR_APPOINTMENT_DTO = "donorAppointmentDTO";
		public static final String SEARCH_COMMUNICATION_HISTORY_REQUEST = "searchCommunicationHistoryRequest";
		public static final String CONFIRM_COMMUNICATION_HISTORY_REQUEST = "confirmCommunicationHistoryRequest";

		public static final String IMPORT_DONOR_EXCEL_REQUEST = "importDonorExcel";
		public static final String GET_REQUEST_LIST_REQUEST = "getRequestListRequest";

		public static final String ADD_DONATION_HISTORY = "addDonationHistory";
		public static final String UNIQUE_REQUEST_DTO = "uniqueRequestDTO";
	}

	public static class ResponseKey
	{
		public static final String SEARCH_DONOR_RESPONSE = "searchDonorResponse";
		public static final String SEARCH_COMMUNICATION_HISTORY_RESPONSE = "searchCommunicationHistoryResponse";
		public static final String CONFIRM_COMMUNICATION_HISTORY_RESPONSE = "confirmCommunicationHistoryResponse";
		public static final String SEARCH_DOATION_CENTER_RESPONSE = "donationCenterResponse";
		public static final String GET_REQUEST_LIST_RESPONSE = "getRequestListResponse";

	}

	public static class HistoryStatus
	{
		public static final String SMS_SENT = "SMS_SENT";
		public static final String CONFIRMED = "CONFIRMED";
		public static final String REJECTED = "REJECTED";
		public static final String CONTACTED = "CONTACTED";
		public static final String RESERVED = "RESERVED";
	}
	
	public static class ServiceName
	{
		public static final String SEARCH_DONOR = "searchdonor";
		public static final String SEND_SMS = "sendSMSToDonors";
		public static final String SEARCH_COMMUNICATION_HISTORY = "searchcommunicationhistory";
		public static final String CONFIRM_COMMUNICATION_HISTORY = "confirmcommunicationhistory";
		public static final String IMPORT_DONOR_DATA = "importdonordata";
		public static final String GET_DONATION_CENTERS = "getdonationcenters";
		public static final String GET_REQUEST_LIST = "getrequestlist";
		public static final String ADD_DONATION_HISTORY = "addDonationHistory";
		public static final String CLOSE_REQUEST = "closeRequest";
		public static final String WITHDRAW_REQUEST = "withdrawRequest";
	}

	public static class RequestMapping
	{
		private static final String BASE_URL = "/";
		public static final String SEARCH_DONOR = BASE_URL + ServiceName.SEARCH_DONOR;

		public static final String IMPORT_DONOR_DATA = BASE_URL + ServiceName.IMPORT_DONOR_DATA;
		public static final String SEND_SMS_TO_DONORS = BASE_URL + ServiceName.SEND_SMS;
		public static final String GET_DONATION_CENTERS = BASE_URL + ServiceName.GET_DONATION_CENTERS;
		public static final String COMMUNICATION_HISTORY_SEARCH_SERVICE = BASE_URL + ServiceName.SEARCH_COMMUNICATION_HISTORY;
		public static final String CONFIRM_COMMUNICATION_HISTORY = BASE_URL + ServiceName.CONFIRM_COMMUNICATION_HISTORY;
		public static final String GET_REQUEST_LIST = BASE_URL + ServiceName.GET_REQUEST_LIST;
		public static final String ADD_DONATION_HISTORY = BASE_URL + ServiceName.ADD_DONATION_HISTORY;
		public static final String CLOSE_REQUEST = BASE_URL + ServiceName.CLOSE_REQUEST;
		public static final String WITHDRAW_REQUEST = BASE_URL + ServiceName.WITHDRAW_REQUEST;
	}
}
