package cz.cvut.fel.aos.api.data;

import java.io.Serializable;

/**
 * @author Ondřej Krejčíř
 */
public class Reservation implements Serializable {

	/**
	 * Identificator
	 */
	private Long id;

	/**
	 * Identificator of reserved flight
	 */
	private int flight;

	/**
	 * Count of seats to reserve
	 */
	private int seats;

	/**
	 * Reservation password
	 */
	private String password;

	/**
	 * State of reservation (new, canceled, paid)
	 */
	private String state;

	/**
	 * String value of creation date in format iso8601: YYYY-MM-DDThh:mm:ssTZD
	 */
	private String created;

	/**
	 * URL of REST source of destination
	 */
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
