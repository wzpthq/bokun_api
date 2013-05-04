package is.bokun.dtos.activity;

import is.bokun.dtos.*;
import is.bokun.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ActivityDto extends ProductDto implements WithExtras {

    public String included;
    public String requirements; 
    public String attention;
    
    public String durationType;
    public int duration;

    public String pricingType;
    
    public int minAge;
    public int maxChildrenPerAdult = 4;

    public int childMaxAge = 11;
    public int teenagerMaxAge = 17;
    
    public int childDiscount = 100;
    public int teenagerDiscount = 50;

    public boolean seasonAllYear;

    public int seasonStartDate;
    public int seasonStartMonth;

    public int seasonEndDate;
    public int seasonEndMonth;
    
    public List<WeekdayDto> weekdays = new ArrayList<>();
    
    public String difficultyLevel;
    
    public List<AgendaItemDto> agendaItems = new ArrayList<>();

    public List<StartTimeDto> startTimes = new ArrayList<>();

    public List<BookableExtraDto> bookableExtras = new ArrayList<>();
	
	public ActivityDto() {
		super();
	}
	
	@JsonIgnore
	public StartTimeDto findStartTime(Long id) {
		for (StartTimeDto dto : startTimes) {
			if ( dto.id.equals(id) ) {
				return dto;
			}
		}
		return null;
	}
	
	@JsonIgnore
	public BookableExtraDto findExtra(Long id) {
		for (BookableExtraDto dto : bookableExtras) {
			if ( dto.id.equals(id) ) {
				return dto;
			}
		}
		return null;
	}
	
	@JsonIgnore
	public boolean hasAnyMapLocation() {
		return countMapLocations() > 0;
	}
	
	@JsonIgnore
	public int countMapLocations() {
		int count = 0;
		for (AgendaItemDto item : agendaItems) {
			if ( item.place != null ) {
				count++;
			}
		}
		return count;
	}
	
	@JsonIgnore
	public String[] includedPoints() {
		return toPoints(included);
	}
	
	@JsonIgnore
	public String[] requirementsPoints() {
		return toPoints(requirements);
	}
	
	@JsonIgnore
	public String[] attentionPoints() {
		return toPoints(attention);
	}
	
	@JsonIgnore
	private String[] toPoints(String s) {
		if ( StringUtils.isNullOrEmpty(s) ) {
			return new String[0];
		}
		return s.split("\n");
	}
	
	@JsonIgnore
	public Date getSeasonStart() throws Exception {
		SimpleDateFormat parser = new SimpleDateFormat("dd.MM");
		return parser.parse("" + seasonStartDate + "." + seasonStartMonth);
	}
	
	@JsonIgnore
	public Date getSeasonEnd() throws Exception {
		SimpleDateFormat parser = new SimpleDateFormat("dd.MM");
		return parser.parse("" + seasonEndDate + "." + seasonEndMonth);
	}
	
    @JsonIgnore
    public int getLowestChildPrice() {
    	return getPercentageOfPrice(findLowestPrice(), childDiscount);
    }
    
    @JsonIgnore
    public int getLowestTeenagerPrice() {
    	return getPercentageOfPrice(findLowestPrice(), teenagerDiscount);
    }
    
    @JsonIgnore
    private int getPercentageOfPrice(int price, int discount) {
        int multiplier = 100 - discount;
        double base = price;
        return (int)Math.floor(base * (((double)multiplier) / 100d) + 0.5d);
    }
	
	@JsonIgnore
	public int findLowestPrice() {
		int lowestPrice = -1;
		for (StartTimeDto st : startTimes) {
			int startTimeLowest = st.prices.getLowestPrice();
			if ( lowestPrice == -1 || startTimeLowest < lowestPrice ) {
				lowestPrice = startTimeLowest;
			}
		}
		return lowestPrice;
	}

    @JsonIgnore
    public List<BookableExtraDto> getIncludedExtras() {
        List<BookableExtraDto> list = new ArrayList<>();
        for ( BookableExtraDto e : bookableExtras ) {
            if ( e.included ) {
                list.add(e);
            }
        }
        return list;
    }

    @JsonIgnore
    public List<BookableExtraDto> getOptionalExtras() {
        List<BookableExtraDto> list = new ArrayList<>();
        for ( BookableExtraDto e : bookableExtras ) {
            if ( !e.included ) {
                list.add(e);
            }
        }
        return list;
    }

}
