package cz.cvut.fel.aos.api.data;

import java.io.Serializable;

/**
 * @author Ondřej Krejčíř
 */
public class Flight implements Serializable {

	/**
	 * Identificator
	 */
	private Long id;

	/**
	 * Flight name
	 */
	private String name;

	/**
	 * Identificator of starting destination
	 */
	private int from;

	/**
	 * Identificator of target destination
	 */
	private int to;

	/**
	 * String value of date of departure in format iso8601: YYYY-MM-DDThh:mm:ssTZD
	 */
	private String dateOfDeparture;

	/**
	 * Distance between start and target destination
	 */
	private double distance;

	/**
	 * Price of the flight
	 */
	private double price;

	/**
	 * Total count of seats in the airplane
	 */
	private int seats;

	/**
	 * URL of REST source of flight
	 */
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public String getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(String dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
