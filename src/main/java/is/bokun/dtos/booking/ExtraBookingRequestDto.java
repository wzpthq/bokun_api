package is.bokun.dtos.booking;

public class ExtraBookingRequestDto {

	public Long extraId;
	public int unitCount;
	
	public ExtraBookingRequestDto() {}

	public Long getExtraId() {
		return extraId;
	}

	public void setExtraId(Long extraId) {
		this.extraId = extraId;
	}

	public int getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}
}