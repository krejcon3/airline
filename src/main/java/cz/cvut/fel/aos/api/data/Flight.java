package cz.cvut.fel.aos.api.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by krejcir on 27.10.14.
 */
public class Flight implements Serializable {

	private Long id;
	private String name;
	private int from;
	private int to;
	private String dateOfDeparture;
	private double distance;
	private double price;
	private int seats;
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
