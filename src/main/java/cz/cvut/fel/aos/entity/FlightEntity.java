package cz.cvut.fel.aos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by krejcir on 27.10.14.
 */
@Entity
@Table(name = "flight")
public class FlightEntity extends AbstractEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "flight_name", nullable = false)
	private String name;

	@Column(name = "from_destination", nullable = false)
	private int from;

	@Column(name = "to_destination", nullable = false)
	private int to;

	@Column(name = "departure", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfDeparture;

	@Column(name = "distance", nullable = false)
	private double distance;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "seats", nullable = false)
	private int seats;

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

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
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
}
