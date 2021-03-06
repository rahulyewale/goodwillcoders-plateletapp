package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.IMPORT_DONOR_DATA;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.ImportDonorDataRequestDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.ImportDonorDataService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImportDonorDataServiceController
{

	@Autowired
	private ImportDonorDataService importDonorDataService;

	@RequestMapping(value = IMPORT_DONOR_DATA, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse importDonorData(@RequestBody
	ServiceRequest serviceRequest) throws Exception
	{
		ImportDonorDataRequestDTO importDonorDataRequestDTO = (ImportDonorDataRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.IMPORT_DONOR_EXCEL_REQUEST, ImportDonorDataRequestDTO.class);

		ImportDonorDataRequestDTO importDonorDataRequestDTOResult = importDonorDataService.importDonorData(importDonorDataRequestDTO);

		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));

		return serviceResponse;
	}

}
