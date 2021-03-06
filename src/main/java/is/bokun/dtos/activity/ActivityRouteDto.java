package is.bokun.dtos.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import is.bokun.queries.GeoPoint;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(name = "activityRoute")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityRouteDto {

	public GeoPoint center;
	public int mapZoomLevel;
	
	public GeoPoint start;
	public GeoPoint end;
	public boolean sameStartEnd;
	
	public List<GeoPoint> waypoints = new ArrayList<>();

	public GeoPoint getCenter() {
		return center;
	}

	public void setCenter(GeoPoint center) {
		this.center = center;
	}

	public int getMapZoomLevel() {
		return mapZoomLevel;
	}

	public void setMapZoomLevel(int mapZoomLevel) {
		this.mapZoomLevel = mapZoomLevel;
	}

	public GeoPoint getStart() {
		return start;
	}

	public void setStart(GeoPoint start) {
		this.start = start;
	}

	public GeoPoint getEnd() {
		return end;
	}

	public void setEnd(GeoPoint end) {
		this.end = end;
	}

	public void setSameStartEnd(boolean sameStartEnd) {
		this.sameStartEnd = sameStartEnd;
	}

	public List<GeoPoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<GeoPoint> waypoints) {
		this.waypoints = waypoints;
	}

	public boolean isSameStartEnd() {
		return sameStartEnd;
	}
}
