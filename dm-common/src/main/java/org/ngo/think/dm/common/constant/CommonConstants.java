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
	}
	
	public static class ResponseKey
	{
		public static final String SEARCH_DONOR_RESPONSE = "searchDonorResponse";
		public static final String SEARCH_COMMUNICATION_HISTORY_RESPONSE = "searchCommunicationHistoryResponse";
		public static final String CONFIRM_COMMUNICATION_HISTORY_RESPONSE = "confirmCommunicationHistoryResponse";
	}
	
	public static class ApplicationConstant
	{
		public static final String SMS = "viaSms";
		public static final String CONFIRM_VIA_CALL = "confimrViaCall";
	}
	
	public static class HistoryStatus
	{
		public static final String SMS_SENT = "SMS_SENT";
		public static final String CONFIRMED = "CONFIRMED";
		public static final String CANCELLED = "CANCELLED";
	}
}
