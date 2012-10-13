package dtos.accommodation;

import java.util.Date;
import java.util.List;

import utils.DateUtils;

public class AccommodationAvailabilityReportDto {

	public Date checkInDate, checkOutDate;
	public List<AvailableRoomDto> availableRooms;
	
	public int numberOfNights() {
		return DateUtils.getNightCount(checkInDate, checkOutDate);
	}
}
