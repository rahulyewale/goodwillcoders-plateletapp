package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UniqueRequestDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String requestNumber;
	private String requestedDate;
	private String bloodGroup;
	private int plateletsBags;
	private String status;
	private String closeRemarks;
	private String withdrawRemarks;

	private Long uniqueRequestTxnId;
	private DonationCenterDTO donationCenterDTO;
	private Date donationDate;
	
	private String patientName;
	
	//for counts
	private int confirmedCount;
	private int donatedCount;
	private int rejectCount;
	private int reserveCount;

	public String getRequestNumber()
	{
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber)
	{
		this.requestNumber = requestNumber;
	}


	public String getRequestedDate()
	{
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate)
	{
		this.requestedDate = requestedDate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCloseRemarks()
	{
		return closeRemarks;
	}

	public void setCloseRemarks(String closeRemarks)
	{
		this.closeRemarks = closeRemarks;
	}

	public String getWithdrawRemarks()
	{
		return withdrawRemarks;
	}

	public void setWithdrawRemarks(String withdrawRemarks)
	{
		this.withdrawRemarks = withdrawRemarks;
	}

	public DonationCenterDTO getDonationCenterDTO()
	{
		return donationCenterDTO;
	}

	public void setDonationCenterDTO(DonationCenterDTO donationCenterDTO)
	{
		this.donationCenterDTO = donationCenterDTO;
	}


	public Long getUniqueRequestTxnId()
	{
		return uniqueRequestTxnId;
	}

	public void setUniqueRequestTxnId(Long uniqueRequestTxnId)
	{
		this.uniqueRequestTxnId = uniqueRequestTxnId;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public int getPlateletsBags()
	{
		return plateletsBags;
	}

	public void setPlateletsBags(int plateletsBags)
	{
		this.plateletsBags = plateletsBags;
	}

	public Date getDonationDate()
	{
		return donationDate;
	}

	public void setDonationDate(Date donationDate)
	{
		this.donationDate = donationDate;
	}

	public String getPatientName() {
		
		return patientName;
	}

	public void setPatientName(String patientName) {
		
		this.patientName = patientName;
	}

	public int getConfirmedCount() {
		return confirmedCount;
	}

	public void setConfirmedCount(int confirmedCount) {
		this.confirmedCount = confirmedCount;
	}

	public int getDonatedCount() {
		return donatedCount;
	}

	public void setDonatedCount(int donatedCount) {
		this.donatedCount = donatedCount;
	}

	public int getRejectCount() {
		return rejectCount;
	}

	public void setRejectCount(int rejectCount) {
		this.rejectCount = rejectCount;
	}

	public int getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}

}
