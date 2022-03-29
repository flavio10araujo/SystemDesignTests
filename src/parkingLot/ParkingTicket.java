package parkingLot;

import java.util.Date;

public class ParkingTicket {

	private String ticketNumber;
	private Date issueAt;
	private Date payedAt;
	private double payedAmount;
	private ParkingTicketStatus status;
	
	public boolean saveInDB() {
		// TODO
		return true;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public Date getIssueAt() {
		return issueAt;
	}
	
	public void setIssueAt(Date issueAt) {
		this.issueAt = issueAt;
	}
	
	public Date getPayedAt() {
		return payedAt;
	}
	
	public void setPayedAt(Date payedAt) {
		this.payedAt = payedAt;
	}
	
	public double getPayedAmount() {
		return payedAmount;
	}
	
	public void setPayedAmount(double payedAmount) {
		this.payedAmount = payedAmount;
	}
	
	public ParkingTicketStatus getStatus() {
		return status;
	}
	
	public void setStatus(ParkingTicketStatus status) {
		this.status = status;
	}
}