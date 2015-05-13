package org.ngo.think.dm.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.util.JsonUtil;

public class LocationResponse
{
	String status;
	List<String> originAddresses;
	List<String> destinationAddresses;
	List<RowsHolder> rows;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<String> getOriginAddresses()
	{
		return originAddresses;
	}

	public void setOriginAddresses(List<String> originAddresses)
	{
		this.originAddresses = originAddresses;
	}

	public List<String> getDestinationAddresses()
	{
		return destinationAddresses;
	}

	public void setDestinationAddresses(List<String> destinationAddresses)
	{
		this.destinationAddresses = destinationAddresses;
	}

	public List<RowsHolder> getRows()
	{
		return rows;
	}

	public void setRows(List<RowsHolder> rows)
	{
		this.rows = rows;
	}

	public static void main(String[] args) throws Exception
	{
		Duration duration = new Duration();
		duration.setText("dsdsd");
		duration.setValue("rtt");

		Distance distance = new Distance();
		distance.setText("dsdsd");
		distance.setValue("rtt");

		DistanceDurationHolder distanceDurationHolder = new DistanceDurationHolder();

		distanceDurationHolder.setDistance(distance);
		distanceDurationHolder.setDuration(duration);
		distanceDurationHolder.setStatus("dsd");

		List<DistanceDurationHolder> distanceDurationHolders = new ArrayList<DistanceDurationHolder>();
		distanceDurationHolders.add(distanceDurationHolder);

		RowsHolder holder = new RowsHolder();
		holder.setElements(distanceDurationHolders);

		List<RowsHolder> rows = new ArrayList<RowsHolder>();

		rows.add(holder);

		List<String> originAddresses = new ArrayList<String>();

		originAddresses.add("sampkleORG");

		List<String> destinationAddrr = new ArrayList<String>();

		destinationAddrr.add("sampkleORG");

		LocationResponse locationResposne = new LocationResponse();
		locationResposne.setDestinationAddresses(destinationAddrr);
		locationResposne.setOriginAddresses(originAddresses);
		locationResposne.setRows(rows);
		locationResposne.setStatus("rttt");

		System.out.println(JsonUtil.convertObjectToJson(locationResposne));

	}
}
