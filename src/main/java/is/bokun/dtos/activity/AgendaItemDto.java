package is.bokun.dtos.activity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import is.bokun.dtos.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendaItemDto {

    public Long id;
    public int index;
    public String title;
    public String body;
    public boolean possibleStartPoint;
    public List<String> flags = new ArrayList<>();

    public PlaceDto place;
    
    public PhotoDto keyPhoto;
    public List<PhotoDto> photos = new ArrayList<>();
    
}
