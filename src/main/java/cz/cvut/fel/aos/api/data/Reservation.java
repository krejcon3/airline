package cz.cvut.fel.aos.api.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by krejcir on 27.10.14.
 */
public class Reservation implements Serializable {

	private Long id;
	private int flight;
	private int seats;
	private String password;
	private String state;
	private String created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFlight() {
		return flight;
	}

	public void setFlight(int flight) {
		this.flight = flight;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}
