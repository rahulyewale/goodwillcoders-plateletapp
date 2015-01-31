package org.ngo.think.dm.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.util.JsonUtil;

public class LocationResposne
{
	String status;
	List<String> origin_addresses;
	List<String> destination_addresses;
	List<RowsHolder> rows;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<String> getOrigin_addresses()
	{
		return origin_addresses;
	}

	public void setOrigin_addresses(List<String> origin_addresses)
	{
		this.origin_addresses = origin_addresses;
	}

	public List<String> getDestination_addresses()
	{
		return destination_addresses;
	}

	public void setDestination_addresses(List<String> destination_addresses)
	{
		this.destination_addresses = destination_addresses;
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

		LocationResposne locationResposne = new LocationResposne();
		locationResposne.setDestination_addresses(destinationAddrr);
		locationResposne.setOrigin_addresses(originAddresses);
		locationResposne.setRows(rows);
		locationResposne.setStatus("rttt");

		System.out.println(JsonUtil.convertObjectToJson(locationResposne));

	}
}
