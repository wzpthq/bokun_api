package dtos.accommodation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import queries.AccommodationQuery;
import queries.RoomQuery;

public class AvailableRoomDto {
	
	public Long id;
	public int totalPrice;
	public int maxBookableCount;

    public AccommodationQuery query;
    public List<Integer> guestCounts = new ArrayList<Integer>();

	public RoomTypeDto roomType;

	public AvailableRoomDto(Long id, int totalPrice, int availableAcrossInterval, RoomTypeDto roomType, AccommodationQuery query) {
        this(id, totalPrice, availableAcrossInterval, roomType, query, false);
    }

    public AvailableRoomDto(Long id, int totalPrice, int availableAcrossInterval, RoomTypeDto roomType, AccommodationQuery query, boolean showFullAvailability) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.roomType = roomType;
        this.query = query;

        this.maxBookableCount = roomType.shared && !showFullAvailability
                ? Math.min(availableAcrossInterval, query.totalCapacityNeeded())
                : availableAcrossInterval
                ;

        for ( RoomQuery rq : query.rooms() ) {
            if ( rq.sleepingPlacesNeeded() <= roomType.capacity ) {
                this.guestCounts.add(rq.sleepingPlacesNeeded());
            }
        }
        if ( query.rooms().size() > 1 && query.totalCapacityNeeded() <= roomType.capacity ) {
            this.guestCounts.add(query.totalCapacityNeeded());
        }
        Collections.sort(this.guestCounts);
	}
}
