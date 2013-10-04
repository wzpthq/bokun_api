package is.bokun.dtos.booking;

import is.bokun.dtos.VendorDto;
import is.bokun.dtos.carrental.CarRentalLocationDto;
import is.bokun.utils.DateUtils;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarRentalBookingDto extends ProductBookingDto {

	public Date startDate;
	public Date endDate;
    public int dayCount;


    public CarRentalLocationDto pickupLocation;
	public CarRentalLocationDto dropoffLocation;
	
	public ProductInfoDto carRental;

	public List<CarBookingDto> carBookings = new ArrayList<>();

    @JsonIgnore
	public int getDayCount() {
		return DateUtils.getRentalDayCount(startDate, endDate);
	}

	@JsonIgnore
	public Date getSortDate() {
		return startDate;
	}

	@Override
	public VendorDto getVendor() {
		return carRental.vendor;
	}
	
	@Override
	public String getProductCategory() {
		return "CAR_RENTALS";
	}

    public List<CarBookingDto> getCarBookings() {
        return carBookings;
    }
}
