package org.ngo.think.dm.service;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.dao.DonationCenterDAO;
import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.service.domain.DistanceComparator;
import org.ngo.think.dm.service.domain.DonorFilter;
import org.ngo.think.dm.service.mapper.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SearchDonorService
{

	@Value("${initial.sms.text}")
	private String initialSmsText;

	@Value("${confirm.sms.text}")
	private String confirmSmsText;

	@Autowired
	DonorFilter donorFilter;

	@Autowired
	UniqueRequestTransactionService uniqueRequestTransactionService;

	@Autowired
	DonorDAO donorDAO;

	@Autowired
	DonationCenterDAO centerDAO;

	private static final Pattern DONATION_CENTRE = Pattern.compile("#DONATION_CENTRE#");
	private static final Pattern REQ_UID = Pattern.compile("#REQ_UID#");
	private static final Pattern REQ_DATE = Pattern.compile("#REQ_DATE#");

	@Transactional
	public SearchDonorResponseDTO searchDonor(SearchDonorRequestDTO searchDonorRequestDTO)
	{
		DonationCenter center = null;

		try
		{
			center = centerDAO.findByPrimaryKey(searchDonorRequestDTO.getDonationCentre());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String uniqueRequestNumber = uniqueRequestTransactionService.getUniqueRequestTranactionID(searchDonorRequestDTO);
		
		// Call DAOImpl to get donor master List
		List<Donor> donorList = donorDAO.getAllDonors(searchDonorRequestDTO.getBloodGroup());
		DonorAppointmentDTO appointmentDTO = new DonorAppointmentDTO();
		appointmentDTO.setCenterId(center.getDonationCenterId());
		appointmentDTO.setRequestedDate(searchDonorRequestDTO.getRequestDate());
		appointmentDTO.setRequestTxnId(uniqueRequestNumber);
		
		donorFilter.filterDonorsBasedOnSearchCriteria(searchDonorRequestDTO, donorList,center,appointmentDTO);
		
		Collections.sort(donorList, new DistanceComparator());

		List<DonorDTO> donorDTOList = DonorMapper.toDTOList(donorList);

		String centerDetails = center.getDonationCenterName() + "," + center.getCity() + "," + center.getPinCode();
		String initialSMS = initialSmsText;
		String intialSMSWithCenter = DONATION_CENTRE.matcher(initialSMS).replaceAll(centerDetails);
		String intialSMSWithReqNumber = REQ_UID.matcher(intialSMSWithCenter).replaceAll(uniqueRequestNumber);
		String intialSMSWithReqDate = REQ_DATE.matcher(intialSMSWithReqNumber).replaceAll(DateUtil.dateToString(searchDonorRequestDTO.getRequestDate()));
		initialSMS = intialSMSWithReqDate;

		String confirmSMS = confirmSmsText;
		String confirmSMSWithCenter = DONATION_CENTRE.matcher(confirmSMS).replaceAll(centerDetails);
		String confirmSMSWithReqDate = REQ_DATE.matcher(confirmSMSWithCenter).replaceAll(DateUtil.dateToString(searchDonorRequestDTO.getRequestDate()));
		confirmSMS = confirmSMSWithReqDate;

		SearchDonorResponseDTO searchDonorResponseDTO = new SearchDonorResponseDTO();
		searchDonorResponseDTO.setConfirmSmsText(confirmSMS);
		searchDonorResponseDTO.setIntialSmsText(initialSMS);
		searchDonorResponseDTO.setUniqueRequestId(uniqueRequestNumber);
		searchDonorResponseDTO.setDonorDTOList(donorDTOList);

		return searchDonorResponseDTO;

	}
}
