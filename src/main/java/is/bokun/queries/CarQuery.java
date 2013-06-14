package is.bokun.queries;

import is.bokun.utils.StringUtils;

import java.util.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Query object for searching rental cars.
 *
 * @author Olafur Gauti Gudmundsson
 */
public class CarQuery extends AbstractDateRangeQuery {
	
	public static final String[] DATE_FORMATS = {"dd.MM.yy HH:mm", "dd.MM.yyyy HH:mm", "yyyy-MM-dd HH:mm"};
	
	/**
	 * Set this to get results for specific car rentals only.
	 */
	public List<Long> carRentalIds = new ArrayList<>();
	
	/**
	 * Set this to get results for the single a car rental that has this (language dependent) slug.
	 */
	public String carRentalSlug;
	
	/**
	 * Set to get only cars that allow this driver age (and above). 
	 */
	public Integer driverAge;
	
	/**
	 * Set to get only cars where passenger capacity is greater than or equal to this.
	 */
	public Integer passengers;
	
	/**
	 * Set to get only cars where luggage capacity is greater than or equal to this.
	 */
	public Integer luggage;
	
	/**
	 * Set to get only cars where door count is greater than or equal to this.
	 */
	public Integer minDoorCount;
	
	/**
	 * Set to filter by CO2 emission (g/km).
	 */
	public NumericRangeFilter co2EmissionFilter;
	
	/**
	 * Set to filter by fuel economy (L/100km).
	 */
	public NumericRangeFilter fuelEconomyFilter;
	
	/**
	 * Set to filter by air conditioning.
	 */
	public Boolean airConditioning;

    /**
     * Find cars that have pickup location that matches this filter.
     */
	public LocationFilters pickupLocationFilters = new LocationFilters();
	
    /**
     * Find cars that have dropoff location that matches this filter.
     */
	public LocationFilters dropoffLocationFilters = new LocationFilters();
	
	/**
	 * Set this to get results for specific pickup locations only.
	 */
	public List<Long> pickupLocationIds = new ArrayList<>();
	
	/**
	 * Set this to get results for specific dropoff locations only.
	 */
	public List<Long> dropoffLocationIds = new ArrayList<>();

	public CarQuery() {
		super(DATE_FORMATS);
	}

	public List<Long> getCarRentalIds() {
		return carRentalIds;
	}

	public void setCarRentalIds(List<Long> carRentalIds) {
		this.carRentalIds = carRentalIds;
	}

	public Integer getDriverAge() {
		return driverAge;
	}

	public void setDriverAge(Integer driverAge) {
		this.driverAge = driverAge;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public Integer getLuggage() {
		return luggage;
	}

	public void setLuggage(Integer luggage) {
		this.luggage = luggage;
	}

	public Integer getMinDoorCount() {
		return minDoorCount;
	}

	public void setMinDoorCount(Integer minDoorCount) {
		this.minDoorCount = minDoorCount;
	}

	public LocationFilters getPickupLocationFilters() {
		return pickupLocationFilters;
	}

	public void setPickupLocationFilters(LocationFilters pickupLocationFilters) {
		this.pickupLocationFilters = pickupLocationFilters;
	}

	public LocationFilters getDropoffLocationFilters() {
		return dropoffLocationFilters;
	}

	public void setDropoffLocationFilters(LocationFilters dropoffLocationFilters) {
		this.dropoffLocationFilters = dropoffLocationFilters;
	}

	public List<Long> getPickupLocationIds() {
		return pickupLocationIds;
	}

	public void setPickupLocationIds(List<Long> pickupLocationIds) {
		this.pickupLocationIds = pickupLocationIds;
	}

	public List<Long> getDropoffLocationIds() {
		return dropoffLocationIds;
	}

	public void setDropoffLocationIds(List<Long> dropoffLocationIds) {
		this.dropoffLocationIds = dropoffLocationIds;
	}

	public NumericRangeFilter getCo2EmissionFilter() {
		return co2EmissionFilter;
	}

	public void setCo2EmissionFilter(NumericRangeFilter co2EmissionFilter) {
		this.co2EmissionFilter = co2EmissionFilter;
	}

	public NumericRangeFilter getFuelEconomyFilter() {
		return fuelEconomyFilter;
	}

	public void setFuelEconomyFilter(NumericRangeFilter fuelEconomyFilter) {
		this.fuelEconomyFilter = fuelEconomyFilter;
	}

	public Boolean getAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(Boolean airConditioning) {
		this.airConditioning = airConditioning;
	}
	
	public String getCarRentalSlug() {
		return carRentalSlug;
	}

	public void setCarRentalSlug(String carRentalSlug) {
		this.carRentalSlug = carRentalSlug;
	}

	@JsonIgnore
	public CarSortField sortField() {
		if ( StringUtils.isNullOrEmpty(sortField) ) {
			return null;
		} else {
			try {
				return CarSortField.valueOf(sortField.toUpperCase());
			} catch ( Throwable ignored ) {
				return null;
			}
		}
	}
}