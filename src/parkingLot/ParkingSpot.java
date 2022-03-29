package parkingLot;

public class ParkingSpot {

	private String number;
	private boolean free;
	private Vehicle vehicle;
	private final ParkingSpotType type;

	public boolean IsFree() {
		// TODO
		return true;
	}

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public boolean assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		free = false;
		// TODO
		return true;
	}

	public boolean removeVehicle() {
		this.vehicle = null;
		free = true;
		// TODO
		return true;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSpotType getType() {
		return type;
	}
}