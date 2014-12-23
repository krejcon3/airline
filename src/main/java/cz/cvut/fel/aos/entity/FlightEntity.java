package cz.cvut.fel.aos.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Describes flight entity
 *
 * @author Ondřej Krejčíř
 */
@Entity
@Table(name = "flight")
public class FlightEntity extends AbstractEntity {

	/**
	 * Identificator
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * Flight name
	 */
	@Column(name = "flight_name", nullable = false)
	private String name;

	/**
	 * Identificator of starting destination
	 */
	@Column(name = "from_destination", nullable = false)
	private int from;

	/**
	 * Identificator of target destination
	 */
	@Column(name = "to_destination", nullable = false)
	private int to;

	/**
	 * Date of departure
	 */
	@Column(name = "departure", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfDeparture;

	/**
	 * Distance between start and target destination
	 */
	@Column(name = "distance", nullable = false)
	private double distance;

	/**
	 * Price of the flight
	 */
	@Column(name = "price", nullable = false)
	private double price;

	/**
	 * Total count of seats in the airplane
	 */
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
