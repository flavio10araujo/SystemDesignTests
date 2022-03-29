package parkingLot;

import java.util.Map;

public class ParkingLot {
	
	private String name;
	private Address address;
	
	private ParkingRate parkingRate;

	private int compactSpotCount;
	private int largeSpotCount;
	private int motorbikeSpotCount;
	private int electricSpotCount;
	
	private final int maxCompactCount = 100;
	private final int maxLargeCount = 25;
	private final int maxMotorbikeCount = 25;
	private final int maxElectricCount = 10;
	
	private Map<String, EntrancePanel> entrancePanels;
	private Map<String, ExitPanel> exitPanels;
	private Map<String, ParkingFloor> parkingFloors;
	private Map<String, ParkingAttendantPortal> parkingAttendantPortals;

	// all active parking tickets, identified by their ticketNumber
	private Map<String, ParkingTicket> activeTickets;

	// singleton ParkingLot to ensure only one object of ParkingLot in the system,
	// all entrance panels will use this object to create new parking ticket: getNewParkingTicket(),
	// similarly exit panels will also use this object to close parking tickets
	private static ParkingLot parkingLot = null;

	// private constructor to restrict for singleton
	private ParkingLot() {
		// 1. initialize variables: read name, address and parkingRate from database
		// 2. initialize parking floors: read the parking floor map from database,
		//  this map should tell how many parking spots are there on each floor. This
		//  should also initialize max spot counts too.
		// 3. initialize parking spot counts by reading all active tickets from database
		// 4. initialize entrance and exit panels: read from database
	}

	// static method to get the singleton instance of ParkingLot
	public static ParkingLot getInstance() {
		if (parkingLot == null) {
			parkingLot = new ParkingLot();
		}
		
		return parkingLot;
	}

	// note that the following method is 'synchronized' to allow multiple entrances
	// panels to issue a new parking ticket without interfering with each other
	public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
		if (this.isFull(vehicle.getType())) {
			throw new ParkingFullException("The parking is full.");
		}
		
		ParkingTicket ticket = new ParkingTicket();
		vehicle.assignTicket(ticket);
		ticket.saveInDB();
		
		// if the ticket is successfully saved in the database, we can increment the parking spot count
		this.incrementSpotCount(vehicle.getType());
		this.activeTickets.put(ticket.getTicketNumber(), ticket);
		return ticket;
	}

	public boolean isFull(VehicleType type) {
		// trucks and vans can only be parked in LargeSpot
		if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			return largeSpotCount >= maxLargeCount;
		}

		// motorbikes can only be parked at motorbike spots
		if (type == VehicleType.MOTORBIKE) {
			return motorbikeSpotCount >= maxMotorbikeCount;
		}

		// cars can be parked at compact or large spots
		if (type == VehicleType.CAR) {
			return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
		}

		// electric car can be parked at compact, large or electric spots
		return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount + maxElectricCount);
	}

	// increment the parking spot count based on the vehicle type
	private void incrementSpotCount(VehicleType type) {
		if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			largeSpotCount++;
		} else if (type == VehicleType.MOTORBIKE) {
			motorbikeSpotCount++;
		} else if (type == VehicleType.CAR) {
			if (compactSpotCount < maxCompactCount) {
				compactSpotCount++;
			} else {
				largeSpotCount++;
			}
		} else { // electric car
			if (electricSpotCount < maxElectricCount) {
				electricSpotCount++;
			} else if (compactSpotCount < maxCompactCount) {
				compactSpotCount++;
			} else {
				largeSpotCount++;
			}
		}
	}

	public boolean isFull() {
		for (String key : parkingFloors.keySet()) {
			if (!parkingFloors.get(key).isFull()) {
				return false;
			}
		}
		
		return true;
	}

	public void addParkingFloor(ParkingFloor floor) {
		/* store in database */ 
	}

	public void addEntrancePanel(EntrancePanel entrancePanel) {
		/* store in database */ 
	}

	public void addExitPanel(ExitPanel exitPanel) {
		/* store in database */ 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Map<String, EntrancePanel> getEntrancePanels() {
		return entrancePanels;
	}

	public void setEntrancePanels(Map<String, EntrancePanel> entrancePanels) {
		this.entrancePanels = entrancePanels;
	}

	public Map<String, ExitPanel> getExitPanels() {
		return exitPanels;
	}

	public void setExitPanels(Map<String, ExitPanel> exitPanels) {
		this.exitPanels = exitPanels;
	}

	public Map<String, ParkingAttendantPortal> getParkingAttendantPortals() {
		return parkingAttendantPortals;
	}

	public void setParkingAttendantPortals(Map<String, ParkingAttendantPortal> parkingAttendantPortals) {
		this.parkingAttendantPortals = parkingAttendantPortals;
	}

	public ParkingRate getParkingRate() {
		return parkingRate;
	}

	public void setParkingRate(ParkingRate parkingRate) {
		this.parkingRate = parkingRate;
	}
}

@SuppressWarnings("serial")
class ParkingFullException extends Exception {
	public ParkingFullException(String errorMessage) {
        super(errorMessage);
    }
}