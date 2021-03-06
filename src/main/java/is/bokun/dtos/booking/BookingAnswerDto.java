package is.bokun.dtos.booking;

import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(name = "bookingAnswer")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingAnswerDto {

	public String type;
	public String question;
	public String answer;
	public String group;
	
	public BookingAnswerDto() {}
	
	public BookingAnswerDto(String type, String answer) {
		this(null, type, answer);
	}
	
	public BookingAnswerDto(String group, String type, String answer) {
		this.group = group;
		this.type = type;
		this.answer = answer;
	}
	
	public BookingAnswerDto(String group, String type, String answer, String question) {
		this(group, type, answer);
		this.question = question;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
}
