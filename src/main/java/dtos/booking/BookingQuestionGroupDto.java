package dtos.booking;

import java.util.*;

public class BookingQuestionGroupDto {

	public String name;
	public int answersNeeded;
	public List<BookingQuestionDto> questions = new ArrayList<BookingQuestionDto>();
}