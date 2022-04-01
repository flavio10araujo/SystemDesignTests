package parkingLot;

import parkingLot.enums.VehicleType;

public abstract class Vehicle {

	private String licenseNumber;
	private final VehicleType type;
	private ParkingTicket ticket;

	public Vehicle(VehicleType type) {
		this.type = type;
	}

	public void assignTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

	public void setTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

	public VehicleType getType() {
		return type;
	}
}