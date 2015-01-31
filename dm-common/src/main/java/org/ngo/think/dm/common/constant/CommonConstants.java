package org.ngo.think.dm.common.constant;

import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;

public class CommonConstants
{
	public static class RequestKey
	{
		public static final String SEARCH_DONOR_REQUEST = "searchDonorRequest";
		public static final String SEARCH_COMMUNICATION_HISTORY_REQUEST = "searchCommunicationHistoryRequest";
	}
	
	public static class ResponseKey
	{
		public static final String SEARCH_DONOR_RESPONSE = "searchDonorResponse";
		public static final String SEARCH_COMMUNICATION_HISTORY_RESPONSE = "searchCommunicationHistoryResponse";
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
