package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.CLOSE_REQUEST;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.GET_REQUEST_LIST;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.WITHDRAW_REQUEST;

import java.util.List;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.GetRequestListResponse;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.InsufficientDonationException;
import org.ngo.think.dm.service.UniqueRequestTransactionService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestServiceController
{

	@Autowired
	private UniqueRequestTransactionService requestTransactionService;

	@RequestMapping(value = GET_REQUEST_LIST, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse getRequestList(@RequestBody
	ServiceRequest serviceRequest)
	{

		GetRequestListInputDTO getRequestListInputDTO = (GetRequestListInputDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.GET_REQUEST_LIST_REQUEST, GetRequestListInputDTO.class);

		List<UniqueRequestDTO> requestDTOList = requestTransactionService.getRequestList(getRequestListInputDTO);

		GetRequestListResponse getRequestListResponse = new GetRequestListResponse();
		getRequestListResponse.setRequestListOutputList(requestDTOList);

		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));

		serviceResponse.add(CommonConstants.ResponseKey.GET_REQUEST_LIST_RESPONSE, getRequestListResponse);

		return serviceResponse;
	}

	// close request
	@RequestMapping(value = CLOSE_REQUEST, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse closeRequest(@RequestBody
	ServiceRequest serviceRequest)
	{

		UniqueRequestDTO uniqueRequestDTO = (UniqueRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.UNIQUE_REQUEST_DTO, UniqueRequestDTO.class);

		ServiceResponse serviceResponse = new ServiceResponse(ResponseData.successResponseData);
		try
		{
			requestTransactionService.closeRequest(uniqueRequestDTO);
		}
		catch (InsufficientDonationException e)
		{
			serviceResponse = new ServiceResponse(ResponseData.errorResponseData);
			serviceResponse.getResponseData().setMessage(e.getMessage());
		}
		catch (Exception exception)
		{
			// TODO
		}
		return serviceResponse;
	}

	// withdraw request
	@RequestMapping(value = WITHDRAW_REQUEST, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse withdrawRequest(@RequestBody
	ServiceRequest serviceRequest)
	{
		UniqueRequestDTO uniqueRequestDTO = (UniqueRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.UNIQUE_REQUEST_DTO, UniqueRequestDTO.class);
		requestTransactionService.withdrawRequest(uniqueRequestDTO);

		ServiceResponse serviceResponse = new ServiceResponse(ResponseData.successResponseData);
		return serviceResponse;
	}
}
