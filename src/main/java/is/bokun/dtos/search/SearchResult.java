package is.bokun.dtos.search;

import java.util.List;

public interface SearchResult {

	public void setTitle(String title);
	public String getTitle();
	
	public void setSummary(String summary);
	public String getSummary();
	
	public void setKeywords(List<String> keywords);
	public List<String> getKeywords();
}